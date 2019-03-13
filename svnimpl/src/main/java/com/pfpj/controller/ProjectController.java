package com.pfpj.controller;

import com.pfpj.api.ProjectApi;
import com.pfpj.entity.Project;
import com.pfpj.service.ProjectService;
import com.pfpj.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController implements ProjectApi {
    @Autowired
    private ProjectService projectService;
    @PostMapping("/project")
    @Override
    public ResultStatus addProject(@RequestBody Project project) {
        //判断项目名 项目路径 项目url 是否为空
        if( !StringUtils.isEmpty(project.getProjectName()) && !StringUtils.isEmpty(project.getProjectPath())&& !StringUtils.isEmpty(project.getProjectUrl())){
            boolean flag = projectService.addProject(project);
            if(flag){
                return ResultStatus.resultOk();
            }else{
                return ResultStatus.resultFailed(2,"项目名或项目路径重复");
            }
        }else{
            return ResultStatus.resultFailed(1,"项目信息不完整");
        }
    }
}
