package com.pfpj.controller;

import com.pfpj.api.FolderApi;
import com.pfpj.conf.ProjectConfigure;
import com.pfpj.entity.Folder;
import com.pfpj.service.FolderService;
import com.pfpj.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FolderController  implements FolderApi{
    @Autowired
    public FolderService folderService;
    @Autowired
    public ProjectConfigure projectConfigure;

    @PutMapping("/folder")
    @Override
    public ResultStatus addFolder(@RequestBody Folder folder) {
        if(!StringUtils.isEmpty(folder.getFolderName()) && !StringUtils.isEmpty(folder.getFolderPath())){
            String folderName=folder.getFolderName();
            String folderUrl="";
            boolean b = projectConfigure.getProjectUrl().endsWith("/");
            if(b){
                folderUrl= projectConfigure.getProjectUrl()+folderName.substring(1);
            }else{
                folderUrl= projectConfigure.getProjectUrl()+"/"+folderName;
            }

            return folderService.addFolder(folder,folderUrl);

        }else{
            return ResultStatus.resultFailed(2,"传入信息不完整");
        }
    }
}
