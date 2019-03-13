package com.pfpj.service.impl;

import com.pfpj.conf.ProjectConfigure;
import com.pfpj.dao.UserMapper;
import com.pfpj.entity.SvnUser;
import com.pfpj.exception.ExceptionEnum;
import com.pfpj.exception.PFPJException;
import com.pfpj.service.AuthorityService;
import com.pfpj.service.UserService;
import com.pfpj.utils.FileUtils;
import com.pfpj.vo.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public ProjectConfigure projectConfigure;
    @Autowired
    public UserMapper userMapper;

    @Autowired
    public AuthorityService authorityService;
    public static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public ResultStatus saveOrUpdateUser(SvnUser svnUser) {
        List<SvnUser> svnUserList = userMapper.querySvnUserByUserName(svnUser.getSvnUserName());
        //TODO 如果查询到信息 证明是修改密码操作 否则是新增操作
        if(svnUserList.size()>0){
            //TODO 修改用户密码
            String svnUserPwd = svnUserList.get(0).getSvnUserPwd();
            if(svnUserPwd.trim().equals(svnUser.getSvnUserPwd().trim())){
                //TODO 如果用户名密码相同没必要进行密码修改
                return ResultStatus.resultFailed(3,"当前用户已存在");
            }else{
                //TODO 否则进行用户密码修改操作
                int i = userMapper.updateSvnUser(svnUser);
                if(i==1){
                    //TODO 操作svn文件 修改用户账号密码
                    saveOrUpdateSvnUser();
                    return ResultStatus.resultOk();
                }else{
                    return ResultStatus.resultFailed(4,"更新用户信息失败");
                }
            }
        }else{
            svnUser.setSvnUserStatus("0");// 状态为0 的时候表示用户为可用状态  1 为不可用状态
            int i = userMapper.saveSvnUser(svnUser);
            if(i==1){
                //TODO 操作svn文件 添加用户账号密码
                saveOrUpdateSvnUser();
                return ResultStatus.resultOk();
            }else{
                return ResultStatus.resultFailed(4,"新增用户失败");
            }
        }
    }


    @Transactional
    @Override
    public boolean deleteUser(String svnUserName) {
        List<SvnUser> svnUsers = userMapper.queryBySvnUserName(svnUserName);
        logger.info("svnUsers  query number is "+svnUsers.size());
        if(svnUsers.size()>0){
            int i = userMapper.deleteSvnUser(svnUserName);
            logger.info("delete svnUser number is "+ i);
            if(i==1){
                saveOrUpdateSvnUser();
                logger.info("saveorupdatesvnuser  is success");
                authorityService.deleteBySvnUserName(svnUserName);
                logger.info("deletebySvnUserName  is success");
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }


    }

    /**
     * 操作svn的用户文件
     */
    public void saveOrUpdateSvnUser(){
        //TODO 查询所有可用的用户信息
        List<SvnUser> svnUsers = userMapper.querySvnUserAll();
        //TODO 添加超级管理员
        SvnUser svnUser = new SvnUser();
        svnUser.setSvnUserName(projectConfigure.getUserName());
        svnUser.setSvnUserPwd(projectConfigure.getPasswd());
        svnUsers.add(svnUser);
        boolean flag = FileUtils.saveOrUpdateSvnUser(svnUsers, projectConfigure.getPasswdPath());
        if(!flag){
            throw new PFPJException(
                    ExceptionEnum.SVN_USER_ERROR.getHttpStatus(),
                    ExceptionEnum.SVN_USER_ERROR.getCode(),
                    ExceptionEnum.SVN_USER_ERROR.getMsg()
            );
        }

    }


}
