package com.pfpj.entity;

import java.io.Serializable;

public class AuthorityUserAndFoler implements Serializable {

    private String svnUserName;//svn用户名
    private String folderPath; //文件路径
    private String authority; //权限 读权限r  写权限w   读写权限rw  空是无权限

    public String getSvnUserName() {
        return svnUserName;
    }

    public void setSvnUserName(String svnUserName) {
        this.svnUserName = svnUserName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityUserAndFoler{" +
                "svnUserName='" + svnUserName + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
