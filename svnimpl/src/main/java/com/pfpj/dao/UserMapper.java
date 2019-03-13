package com.pfpj.dao;

import com.pfpj.entity.SvnUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

     List<SvnUser> querySvnUserByUserName(@Param("svnUserName") String svnUserName);

     List<SvnUser> querySvnUserAll();

     int saveSvnUser(SvnUser svnUser);

     int updateSvnUser(SvnUser svnUser);

     int deleteSvnUser(@Param("svnUserName") String svnUserName);

    List<SvnUser> queryBySvnUserName(@Param("svnUserName") String svnUserName);
}
