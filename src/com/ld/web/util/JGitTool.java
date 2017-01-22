package com.ld.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.BranchTrackingStatus;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import com.ld.web.been.dto.GitBranch;
import com.ld.web.been.dto.GitLog;

/**
 * 
 *<p>Title: JGitTool</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-18
 */
public class JGitTool {

    private String localPath; // 本地库地址

    private String remotePath; // 远端地址

    private Repository repo; // 本地库

    private Git git;

    private final String REFS_HEADS = "refs/heads/";

    public void cloneRepo(String branchName) throws Exception {

        CloneCommand cmd = Git.cloneRepository().setURI(remotePath).setDirectory(new File(localPath));

        boolean isCloneAllBranchs = true;

        if (!StringUtil.isEmpty(branchName)) {
            cmd.setBranch(branchName);
            isCloneAllBranchs = false;
        }

        cmd.setCloneAllBranches(isCloneAllBranchs).setBare(false).call();
    }

    public GitBranch getLocalCurrentBranch() throws Exception {

        String remoteTrackingBranch = null;

        BranchTrackingStatus bts = BranchTrackingStatus.of(repo, repo.getFullBranch());
        if (null != bts) {
            remoteTrackingBranch = bts.getRemoteTrackingBranch();
        }

        return new GitBranch(repo.getBranch(), repo.getFullBranch(), remoteTrackingBranch);
    }

    public List<GitBranch> getLocalBranchList() throws Exception {

        List<GitBranch> items = getBranchList(null);

        for (GitBranch b : items) {
            BranchTrackingStatus bts = BranchTrackingStatus.of(repo, b.getFullName());
            if (null != bts) {
                b.setTrackRemoteName(bts.getRemoteTrackingBranch());
            }
        }

        return items;
    }

    public List<GitBranch> getAllBranchList() throws Exception {
        return getBranchList(ListMode.ALL);
    }

    public List<GitBranch> getRemoteBranchList() throws Exception {
        return getBranchList(ListMode.REMOTE);
    }

    public List<GitBranch> getBranchList(ListMode model) throws Exception {

        List<GitBranch> items = new ArrayList<GitBranch>();

        List<Ref> branchs = git.branchList().setListMode(model).call();

        for (Ref ref : branchs) {
            int index = ref.getName().lastIndexOf("/") > 0 ? ref.getName().lastIndexOf("/") : 0;

            items.add(new GitBranch(ref.getName().substring(index + 1), ref.getName()));
        }

        return items;
    }

    public boolean isExistBranch(String branchName) throws Exception {

        String prefixName = branchName.startsWith(REFS_HEADS) ? "" : REFS_HEADS;

        String fullBranchName = prefixName + branchName;

        List<Ref> refs = git.branchList().call();
        for (Ref ref : refs) {
            if (ref.getName().equals(fullBranchName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteLocalBranch(String branchName) throws Exception {

        if (isExistBranch(branchName)) {
            git.branchDelete().setBranchNames(branchName).setForce(true).call();
        }
    }

    public void createNewBranch(String branchName, String trackBranchName) throws Exception {

        deleteLocalBranch(branchName);

        CreateBranchCommand cmd = git.branchCreate().setName(branchName).setForce(true);

        if (!StringUtil.isEmpty(trackBranchName)) {
            cmd.setStartPoint(trackBranchName).setUpstreamMode(SetupUpstreamMode.SET_UPSTREAM);
        }
        cmd.call();
    }

    public void renameBranch(String OldBranchName, String newBranchName) throws Exception {

        if (isExistBranch(OldBranchName)) {
            git.branchRename().setOldName(OldBranchName).setNewName(newBranchName).call();
        }
    }

    public void switchBranch(String branchName) throws Exception {

        git.checkout().setCreateBranch(!isExistBranch(branchName)).setName(branchName).call();
    }

    public void trackBranch(GitBranch branch) throws Exception {

        git.branchCreate().setName(branch.getName()).setUpstreamMode(SetupUpstreamMode.TRACK).setStartPoint(branch.getFullName()).call();
    }

    public void pull() throws Exception {

        git.pull().call();
    }

    public List<GitLog> getLogs(int maxCount) throws Exception {

        List<GitLog> items = new ArrayList<GitLog>();

        Iterable<RevCommit> iter = git.log().setMaxCount(maxCount).call();

        for (Iterator<RevCommit> i = iter.iterator(); i.hasNext();) {
            RevCommit c = i.next();

            items.add(new GitLog(c.getShortMessage(), c.getAuthorIdent().getName(), c.getFullMessage(), c.getAuthorIdent().getWhen(), c.getAuthorIdent().getEmailAddress()));
        }
        return items;
    }

    public JGitTool(String localPath, String remotePath) throws Exception {
        this.localPath = localPath;
        this.remotePath = remotePath;

        this.repo = new FileRepository(localPath + File.separator + ".git");
        this.git = new Git(repo);
    }

}
