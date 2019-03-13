package com.pfpj.service;

import com.pfpj.entity.AuthorityUserAndFoler;
import com.pfpj.vo.ResultStatus;

public interface AuthorityService {
     ResultStatus authorityByUserAndFolder(AuthorityUserAndFoler authorityUserAndFoler);

     void deleteBySvnUserName(String svnUserName);
}
