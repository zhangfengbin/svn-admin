package com.pfpj.entity;

import java.io.Serializable;

public class Project implements Serializable{
    private String projectId;//svn项目id
    private String projectName;//svn项目名称
    private String projectPath;//svn项目所在服务器绝对路径
    private String projectUrl;//svn项目的请求路径
    private String projectMsg;//svn项目描述信息

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getProjectMsg() {
        return projectMsg;
    }

    public void setProjectMsg(String projectMsg) {
        this.projectMsg = projectMsg;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", projectUrl='" + projectUrl + '\'' +
                ", projectMsg='" + projectMsg + '\'' +
                '}';
    }
}
