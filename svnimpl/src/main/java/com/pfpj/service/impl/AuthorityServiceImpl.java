package com.pfpj.service.impl;

import com.pfpj.conf.ProjectConfigure;
import com.pfpj.dao.AuthorityMapper;
import com.pfpj.dao.FolderMapper;
import com.pfpj.dao.UserMapper;
import com.pfpj.entity.AuthorityUserAndFoler;
import com.pfpj.entity.Folder;
import com.pfpj.entity.SvnUser;
import com.pfpj.service.AuthorityService;
import com.pfpj.utils.FileUtils;
import com.pfpj.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    ProjectConfigure projectConfigure;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public FolderMapper folderMapper;

    @Autowired
    public AuthorityMapper authorityMapper;
    @Transactional
    @Override
    public ResultStatus authorityByUserAndFolder(AuthorityUserAndFoler authorityUserAndFoler) {
        //TODO 先通过传入的信息 查询用户表 是否有用户存在
        List<SvnUser> svnUserList = userMapper.queryBySvnUserName(authorityUserAndFoler.getSvnUserName());
        if(svnUserList.size()==0){
            return ResultStatus.resultFailed(2,"用户不存在");

        }
        //TODO 查询文件表 查询是否文件路径存在
        List<Folder> folderList = folderMapper.queryFolderByFolderPath(authorityUserAndFoler.getFolderPath());
        if(folderList.size()==0){
            return ResultStatus.resultFailed(3,"文件路径不存在");

        }
        //TODO 查询文件用户权限表 查询当前用户对用的文件路径是否存在
        List<AuthorityUserAndFoler> authorityUserAndFolerList= authorityMapper.queryAuthority(authorityUserAndFoler);
        if(authorityUserAndFolerList.size()==1){
            //TODO 通过用户名和文件路径进行查询  如果有查询结果 就进行更新操作
            if(authorityUserAndFolerList.get(0).getAuthority().equals(authorityUserAndFoler.getAuthority())){
                return ResultStatus.resultFailed(3,"权限和原有权限一致，无需更改");
            }else{
                authorityMapper.updateAuthority(authorityUserAndFoler);
                //TODO 更新权限配置文件
                updateAuthzFile();
                return ResultStatus.resultOk();
            }
        }else{
            //TODO 如果没有查询结果就进行新增操作
            authorityMapper.saveAuthority(authorityUserAndFoler);
            //TODO 更新权限配置文件
            updateAuthzFile();
            return ResultStatus.resultOk();
        }

    }

    /**
     * 通过svnUserName 删除权限表中的数据
     * @param svnUserName
     */
    @Override
    public void deleteBySvnUserName(String svnUserName) {
       int i = authorityMapper.deleteBySvnUserName(svnUserName);
       if(i>0){
           updateAuthzFile();
       }
    }

    public void updateAuthzFile(){
        List<AuthorityUserAndFoler> authorityUserAndFolerList = authorityMapper.queryAuthorityall();
        if(authorityUserAndFolerList.size()>0){
            //TODO 对权限为空的用户做过滤
            Iterator<AuthorityUserAndFoler> iterator = authorityUserAndFolerList.iterator();
            while (iterator.hasNext()){
                AuthorityUserAndFoler authorityUserAndFoler = iterator.next();
                if(StringUtils.isEmpty(authorityUserAndFoler.getAuthority())){
                    iterator.remove();
                }
            }
        }

        AuthorityUserAndFoler authorityUserAndFoler = new AuthorityUserAndFoler();
        authorityUserAndFoler.setSvnUserName(projectConfigure.getUserName());
        authorityUserAndFoler.setFolderPath("/");
        authorityUserAndFoler.setAuthority("rw");
        authorityUserAndFolerList.add(authorityUserAndFoler);
        FileUtils.saveOrUpdateAuthz(authorityUserAndFolerList,projectConfigure.getProjectName(),projectConfigure.getAuthzPath());

    }
}
