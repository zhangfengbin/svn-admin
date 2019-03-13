package com.pfpj.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pro")
public class ProjectConfigure {
    private String projectName;
    private String projectPath;
    private String projectUrl;
    private String userName;
    private String passwd;
    private String passwdPath;
    private String authzPath;

    public String getPasswdPath() {
        return passwdPath;
    }

    public void setPasswdPath(String passwdPath) {
        this.passwdPath = passwdPath;
    }

    public String getAuthzPath() {
        return authzPath;
    }

    public void setAuthzPath(String authzPath) {
        this.authzPath = authzPath;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
