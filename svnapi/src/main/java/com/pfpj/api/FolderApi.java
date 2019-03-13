package com.pfpj.api;

import com.pfpj.entity.Folder;
import com.pfpj.vo.ResultStatus;

/**
 * 文件夹管理解耦
 */
public interface FolderApi {

    /**
     * svn资源库新增文件夹
     * @param  folderName//文件夹名称
     * @param folderPath //当前文件夹的url
     * @return
     */
    public ResultStatus addFolder(  Folder folder);

}
