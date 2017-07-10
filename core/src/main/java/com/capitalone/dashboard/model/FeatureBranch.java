package com.capitalone.dashboard.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="feauture_branch")
public class FeatureBranch extends BaseModel {
    
    private long firstCommitTimeStamp;
    private long deployTimeStamp;
    private String commitIdFirstCommit;
    private String commitIdThatTriggeredDeploy;
    private String gitRepoUrl;
    private String name;


    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }

    public long getFirstCommitTimeStamp()
    {
        return firstCommitTimeStamp;
    }

    public void setFirstCommitTimeStamp(long firstCommitTimeStamp)
    {
        this.firstCommitTimeStamp = firstCommitTimeStamp;
    }

    public long getDeployTimeStamp()
    {
        return deployTimeStamp;
    }

    public void setDeployTimeStamp(long deployTimeStamp)
    {
        this.deployTimeStamp = deployTimeStamp;
    }

    public String getCommitIdThatTriggeredDeploy()
    {
        return commitIdThatTriggeredDeploy;
    }

    public void setCommitIdThatTriggeredDeploy(String commitIdThatTriggeredDeploy)
    {
        this.commitIdThatTriggeredDeploy = commitIdThatTriggeredDeploy;
    }

    public String getCommitIdFirstCommit()
    {
        return commitIdFirstCommit;
    }

    public void setCommitIdFirstCommit(String commitIdFirstCommit)
    {
        this.commitIdFirstCommit = commitIdFirstCommit;
    }

    public String getGitRepoUrl()
    {
        return gitRepoUrl;
    }

    public void setGitRepoUrl(String gitRepoUrl)
    {
        this.gitRepoUrl = gitRepoUrl;
    }
}
