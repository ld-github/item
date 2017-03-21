package test;

import java.util.List;

import com.ld.web.been.dto.GitBranch;
import com.ld.web.util.JGitTool;
import com.ld.web.util.JsonMapper;

public class JGitToolTest {

    private static final String LOCAL_PATH = "E:\\testTool";
    private static final String USERNAME = "1111";
    private static final String PASSWORD = "1111";
    private static final String REMOTE_PATH = "git@git.zhihuianxin.com:root/school_platform.git";

    public static void main(String[] args) {
        try {
            new JGitTool(LOCAL_PATH, REMOTE_PATH, USERNAME, PASSWORD).pull();
            System.out.println(JsonMapper.getInstance().toJson(new JGitTool(LOCAL_PATH, REMOTE_PATH, USERNAME, PASSWORD).getLogs(10)));
            System.out.println(JsonMapper.getInstance().toJson(new JGitTool(LOCAL_PATH, REMOTE_PATH, USERNAME, PASSWORD).getLocalCurrentBranch()));

            List<GitBranch> branchs = new JGitTool(LOCAL_PATH, REMOTE_PATH, USERNAME, PASSWORD).getLocalBranchList();
            System.out.println(JsonMapper.getInstance().toJson(branchs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
