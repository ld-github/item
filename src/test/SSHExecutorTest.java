package test;

import com.jcraft.jsch.JSchException;
import com.ld.web.been.dto.SSHLoginInfo;
import com.ld.web.util.JsonMapper;
import com.ld.web.util.SSHExecuter;

public class SSHExecutorTest {

    static final String HOST = "192.168.0.8";
    static final int PORT = 22;
    static final String USERNAME = "axinplatform";
    static final String PASSWORD = "axinfplatform78";

    public static void main(String[] args) {

        try {
            SSHLoginInfo info = new SSHLoginInfo(HOST, PORT, USERNAME, PASSWORD);

            SSHExecuter executer = new SSHExecuter(info);
            System.out.println("Login success!");

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("ls")));

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("pwd")));

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("cd apache-tomcat-7.0.72-payback && ls")));

            System.out.println(JsonMapper.getInstance().toJson(executer.excuteCmd("pwd")));

            executer.close();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
