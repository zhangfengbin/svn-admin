package com.pfpj.controller;

import com.pfpj.api.AuthorityApi;
import com.pfpj.entity.AuthorityUserAndFoler;
import com.pfpj.service.AuthorityService;
import com.pfpj.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorityController implements AuthorityApi {
    @Autowired
    public AuthorityService authorityService;
    @Override
    @PutMapping("/authority")
    public ResultStatus authorityByUserAndFolder(@RequestBody AuthorityUserAndFoler authorityUserAndFoler) {
        if( !StringUtils.isEmpty(authorityUserAndFoler.getFolderPath()) && !StringUtils.isEmpty(authorityUserAndFoler.getSvnUserName())){
            String authority = authorityUserAndFoler.getAuthority();
            if (StringUtils.isEmpty(authority)) {
                authorityUserAndFoler.setAuthority("");
            }else{
                if(!authority.contains("r") || !authority.contains("w") || !authority.contains("rw")){
                    return ResultStatus.resultFailed(1,"权限格式错误");
                }
            }
            return authorityService.authorityByUserAndFolder(authorityUserAndFoler);

        }else{
           return ResultStatus.resultFailed(2,"传入信息不完整");
        }
    }
}
