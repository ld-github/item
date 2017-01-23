package com.ld.web.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
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
 *@date 2017-01-23
 */
public class SSHExecuter implements Closeable {

    private static final Logger logger = Logger.getLogger(SSHExecuter.class);

    private Connection conn; // 链接

    private Session session; // session

    private Vector<String> stdouts; // 返回

    public Vector<String> excuteCmd(String command) throws Exception {

        try {
            synchronized (logger) {

                session = conn.openSession();

                stdouts = new Vector<String>();

                session.execCommand(command);

                InputStream stdout = new StreamGobbler(session.getStdout());

                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

                long start = System.currentTimeMillis();

                while (true) {
                    String line = br.readLine();

                    if (line == null || (System.currentTimeMillis() - start) > 10000) {
                        break;
                    }

                    stdouts.add(line);
                }

                br.close();

                return stdouts;
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            session.close();
        }
    }

    public SSHExecuter(SSHLoginInfo info) throws Exception {

        if (null == session) {

            conn = new Connection(info.getHost(), info.getPort());

            conn.connect();

            boolean isAuthenticated = conn.authenticateWithPassword(info.getUsername(), info.getPassword());

            if (isAuthenticated == false) {
                throw new IOException("Authentication failed.");
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (null != conn) {
            conn.close();
        }
    }

}
