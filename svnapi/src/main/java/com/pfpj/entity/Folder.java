package com.pfpj.entity;

import java.io.Serializable;

public class Folder implements Serializable{

    private String folderName;//文件夹名称
    private String folderPath; //当前文件夹的url



    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public String toString() {
        return "Folder{" +
                ", folderName='" + folderName + '\'' +
                ", folderPath='" + folderPath + '\'' +
                '}';
    }
}
