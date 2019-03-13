package com.pfpj.api;

import com.pfpj.entity.AuthorityUserAndFoler;
import com.pfpj.vo.ResultStatus;

/**
 * 文件夹和用户权限管理接口
 */
public interface AuthorityApi {
    /**
     * 设置用户关联文件夹权限
     * @param  svnUserName//svn用户名
     * @param folderPath //文件路径
     * @param authority //权限 读权限r  写权限w   读写权限rw  空是无权限
     * @return
     */
    public ResultStatus authorityByUserAndFolder(  AuthorityUserAndFoler authorityUserAndFoler);
}
