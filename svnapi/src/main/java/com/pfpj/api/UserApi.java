package com.pfpj.api;

import com.pfpj.entity.SvnUser;
import com.pfpj.vo.ResultStatus;

/**
 * 用户管理接口
 */
public interface UserApi {

    /**
     * 新增用户或修改用户(如果接收的有用户id就是修改用户操作)
     * @param svnUserName svn用户名
     * @param svnUserPwd  svn用户密码
     * @return
     */
    public ResultStatus saveOrUpdateUser( SvnUser svnUser);



    /**
     * 刪除用戶
     * @param svnUserName svn用户名
     * @return
     */
    public ResultStatus deleteUser(SvnUser svnUserName);


}
