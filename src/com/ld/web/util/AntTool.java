package com.ld.web.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;

public class AntTool {

    private Project prj;

    public void copyFolder(String srcDir, String toDir) {

        prj = new Project();
        prj.setBaseDir(new File(srcDir));

        Copy copy = new Copy();
        copy.setProject(prj);

        FileSet fileSet = new FileSet();

        fileSet.setDir(new File(srcDir));

        copy.addFileset(fileSet);
        copy.setTodir(new File(toDir));

        copy.execute();
    }

}
