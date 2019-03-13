package com.pfpj.dao;

import com.pfpj.entity.AuthorityUserAndFoler;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    List<AuthorityUserAndFoler> queryAuthority(AuthorityUserAndFoler authorityUserAndFoler);

    int updateAuthority(AuthorityUserAndFoler authorityUserAndFoler);

    int saveAuthority(AuthorityUserAndFoler authorityUserAndFoler);

    List<AuthorityUserAndFoler> queryAuthorityall();

    int deleteBySvnUserName(String svnUserName);
}
