package test;

import java.io.File;

import com.ld.web.been.dto.SSHLoginInfo;
import com.ld.web.util.JsonMapper;
import com.ld.web.util.SSHExecuter;

public class SSHExecutorTest {

    static final String HOST = "192.168.0.8";
    static final int PORT = 22;
    static final String USERNAME = "axinplatform";
    static final String PASSWORD = "axinfplatform78";

    static final String LOCAL_DIR = "C:\\Users\\ibm\\Desktop\\用友日志111";
    static final String LOCAL_FILE_PATE = "C:\\Users\\ibm\\Desktop\\用友日志\\payment_system_yongyou.log.4";
    static final String REMOTE_FILE_DIR = "/home/axinplatform/test_sftp_upload/";

    public static void main(String[] args) {

        try {
            SSHLoginInfo info = new SSHLoginInfo(HOST, PORT, USERNAME, PASSWORD);

            SSHExecuter executer = new SSHExecuter(info);
            System.out.println("Login success!");

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("ls")));

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("pwd")));

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("cd apache-tomcat-7.0.72-payback && ls")));
            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("cd /home/axinplatform/test_sftp_upload && ls")));

            executer.close();

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("pwd")));
            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("ps -ef|grep tomcat")));

//            executer.uploadFile(LOCAL_FILE_PATE, null, REMOTE_FILE_DIR);
//            executer.uploadDir(LOCAL_DIR, REMOTE_FILE_DIR);

            executer.downloadFile("/home/axinplatform/test_sftp_upload/newFile.jsp", LOCAL_DIR + File.separator + "cc.asp");
            executer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
