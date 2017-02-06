package com.ld.web.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.ld.web.been.dto.SSHLoginInfo;

/**
 * 
 *<p>Title: SSHExecuter</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-02-04
 */
public class SSHExecuter implements Closeable {

    private static final Logger logger = Logger.getLogger(SSHExecuter.class);

    private Connection conn; // 链接

    private Session session; // session

    private Vector<String> stdouts; // 返回信息

    private SSHLoginInfo info; // 登陆信息

    public Vector<String> excuteCmd(String command) throws Exception {

        try {
            synchronized (logger) {

                login();

                session = conn.openSession();

                stdouts = new Vector<String>();

                session.execCommand(command);

                int conditions = session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 5000);

                if ((conditions & ChannelCondition.TIMEOUT) != 0) {
                    throw new IOException("Timeout while waiting for data from peer.");
                }

                InputStream stdout = new StreamGobbler(session.getStdout());
                InputStream stderr = new StreamGobbler(session.getStderr());

                BufferedReader brout = new BufferedReader(new InputStreamReader(stdout));
                BufferedReader brerr = new BufferedReader(new InputStreamReader(stderr));

                long start = System.currentTimeMillis();

                while (true) {
                    String line = brout.readLine();

                    if (line == null || (System.currentTimeMillis() - start) > 10000) {
                        break;
                    }

                    stdouts.add(line);
                }

                while (true) {
                    String line = brerr.readLine();

                    if (line == null || (System.currentTimeMillis() - start) > 10000) {
                        break;
                    }

                    stdouts.add(line);
                }

                brout.close();
                brerr.close();

                return stdouts;
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            session.close();
        }
    }

    public void uploadFile(String localFilePath, String remoteFileName, String remoteDir) throws Exception {

        login();

        SCPClient scpClient = conn.createSCPClient();

        excuteCmd("mkdir " + remoteDir);

        File file = new File(localFilePath);

        if (!file.exists() || file.isDirectory()) {
            throw new Exception("File not found or not is a file error!");
        }

        scpClient.put(localFilePath, StringUtil.isEmpty(remoteFileName) ? file.getName() : remoteFileName, remoteDir, "0600");
    }

    public void uploadDir(String localDir, String remoteDir) throws Exception {

        File dir = new File(localDir);

        if (dir.isFile()) {
            uploadFile(localDir, null, remoteDir);
            return;
        }

        String[] fileList = dir.list();

        for (String name : fileList) {
            String fullPath = localDir + File.separator + name;

            if (new File(fullPath).isDirectory()) {
                String subRemoteDir = remoteDir + "/" + name;

                uploadDir(fullPath, subRemoteDir);
            } else {
                uploadFile(fullPath, null, remoteDir);
            }
        }

    }

    private void login() throws Exception {

        if (null == conn || !conn.isAuthenticationComplete()) {

            conn = new Connection(info.getHost(), info.getPort());

            conn.connect();

            if (!conn.authenticateWithPassword(info.getUsername(), info.getPassword())) {
                throw new IOException("Authentication failed.");
            }
        }
    }

    public SSHExecuter(SSHLoginInfo info) throws Exception {
        this.info = info;
    }

    @Override
    public void close() throws IOException {
        if (null != conn) {
            conn.close();
        }
    }

}
