package com.pfpj.controller;

import com.pfpj.api.UserApi;
import com.pfpj.entity.SvnUser;
import com.pfpj.service.UserService;
import com.pfpj.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController implements UserApi {
    @Autowired
    private UserService userService;
    @PutMapping("/svnUser")
    @Override
    public ResultStatus saveOrUpdateUser(@RequestBody  SvnUser svnUser) {
        if(!StringUtils.isEmpty(svnUser.getSvnUserName()) && !StringUtils.isEmpty(svnUser.getSvnUserPwd())){
            return userService.saveOrUpdateUser(svnUser);

        }else{
            return ResultStatus.resultFailed(2,"传入信息不完整");
        }

    }



    @DeleteMapping("/svnUser")
    @Override
    public ResultStatus deleteUser(@RequestBody SvnUser svnUser) {
        if(!StringUtils.isEmpty(svnUser.getSvnUserName())){
            boolean flag = userService.deleteUser(svnUser.getSvnUserName());
            if(flag){
                return ResultStatus.resultOk();
            }else{
                return ResultStatus.resultFailed(1,"用户删除失败");
            }
        }else{
            return ResultStatus.resultFailed(2,"提供的信息不完整");
        }

    }
}
