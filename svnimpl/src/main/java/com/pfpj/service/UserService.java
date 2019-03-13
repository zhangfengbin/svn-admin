package com.pfpj.service;

import com.pfpj.entity.SvnUser;
import com.pfpj.vo.ResultStatus;

public interface UserService {
    boolean deleteUser(String svnUserName);

    ResultStatus saveOrUpdateUser(SvnUser svnUser);
}
