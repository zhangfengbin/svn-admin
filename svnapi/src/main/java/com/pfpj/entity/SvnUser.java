package com.pfpj.entity;

import java.io.Serializable;

public class SvnUser implements Serializable {
    private String svnUserName;
    private String svnUserPwd;
    private String svnUserStatus;

    public String getSvnUserStatus() {
        return svnUserStatus;
    }

    public void setSvnUserStatus(String svnUserStatus) {
        this.svnUserStatus = svnUserStatus;
    }



    public String getSvnUserName() {
        return svnUserName;
    }

    public void setSvnUserName(String svnUserName) {
        this.svnUserName = svnUserName;
    }

    public String getSvnUserPwd() {
        return svnUserPwd;
    }

    public void setSvnUserPwd(String svnUserPwd) {
        this.svnUserPwd = svnUserPwd;
    }

    @Override
    public String toString() {
        return "SvnUser{" +
                ", svnUserName='" + svnUserName + '\'' +
                ", svnUserPwd='" + svnUserPwd + '\'' +
                ", svnUserStatus='" + svnUserStatus + '\'' +
                '}';
    }
}
