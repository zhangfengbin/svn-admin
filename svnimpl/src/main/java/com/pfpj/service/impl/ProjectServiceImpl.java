package com.pfpj.service.impl;

import com.pfpj.dao.ProjectMapper;
import com.pfpj.entity.Project;
import com.pfpj.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public boolean addProject(Project project) {
        //查询项目名称是否存在
        List<Project> projectList = projectMapper.queryProjects(project);
        if(projectList.size()>0){
            return false;
        }else{
            project.setProjectId(UUID.randomUUID().toString().replace("-",""));
            projectMapper.saveProject(project);
            return true;
        }
    }
}
