package test;

import org.junit.Test;

import com.ld.web.util.AntTool;

public class AntToolTest {

    static final String BASE_DIR = "E:\\testTool\\codesrc\\apiserver";
    static final String ANT_DIR = "E:\\antTest\\apiserver";

    @Test
    public void copyFolder() {
        new AntTool().copyFolder(BASE_DIR, ANT_DIR);
    }

    @Test
    public void war() {

    }
}
