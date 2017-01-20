package com.ld.web.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.FileScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;

/**
 * 
 *<p>Title: AntTool</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-20
 */
public class AntTool {

    private final String PRO_WEB_NAME = "web.name";

    private final String EXECUTE_DEFAULT_TARGET = "war";

    public void copyFolder(String srcDir, String toDir) {

        Copy copy = new Copy();

        FileSet fileSet = new FileSet();
        fileSet.setDir(new File(srcDir));

        copy.setProject(new Project());
        copy.addFileset(fileSet);
        copy.setTodir(new File(toDir));

        copy.execute();
    }

    public List<String> findFiles(String baseDir, String[] filenames) {

        FileScanner ds = new DirectoryScanner();
        ds.setBasedir(new File(baseDir));

        ds.setIncludes(filenames);
        ds.setCaseSensitive(true);
        ds.scan();

        return Arrays.asList(ds.getIncludedFiles());
    }

    public void ant(String baseDir, String webName) {

        Project p = new Project();
        p.setBaseDir(new File(baseDir));

        DefaultLogger consoleLogger = new DefaultLogger();

        consoleLogger.setErrorPrintStream(System.err);

        consoleLogger.setOutputPrintStream(System.out);

        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);

        p.addBuildListener(consoleLogger);

        p.fireBuildStarted();

        p.init();

        File buildFile = new File(baseDir + File.separator + "build.xml");

        ProjectHelper helper = ProjectHelper.getProjectHelper();
        helper.parse(p, buildFile);

        p.setProperty(PRO_WEB_NAME, webName);

        String defaultTarget = StringUtil.isEmpty(p.getDefaultTarget()) ? EXECUTE_DEFAULT_TARGET : p.getDefaultTarget();

        p.executeTarget(defaultTarget);

        p.fireBuildFinished(null);
    }

}
