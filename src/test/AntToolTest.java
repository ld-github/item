package test;

import org.junit.Test;

import com.ld.web.util.AntTool;
import com.ld.web.util.JsonMapper;

public class AntToolTest {

    static final String BASE_DIR = "E:\\testTool\\codesrc\\apiserver";
    static final String ANT_DIR = "E:\\antTest1\\apiserver";

    @Test
    public void copyFolder() {
        new AntTool().copyFolder(BASE_DIR, ANT_DIR);
    }

    @Test
    public void findFile() {
        String[] filenames = { "**\\web.xml" };
        System.out.println(JsonMapper.getInstance().toJson(new AntTool().findFiles(ANT_DIR, filenames)));
    }

    @Test
    public void findFile1() {
        String[] filenames = { "**\\bb.txt" };
        System.out.println(JsonMapper.getInstance().toJson(new AntTool().findFiles(ANT_DIR, filenames)));
    }

    @Test
    public void findAllJavaFile() {
        String[] filenames = { "**\\*.java" };
        System.out.println(JsonMapper.getInstance().toJson(new AntTool().findFiles(ANT_DIR, filenames)));
    }

    @Test
    public void ant() {
        new AntTool().ant(ANT_DIR, "apiserver1");
    }
}
