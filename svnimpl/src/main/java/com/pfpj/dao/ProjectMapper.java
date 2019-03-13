package com.pfpj.dao;

import com.pfpj.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    /**
     * 通过条件查询项目信息
     * @param project
     * @return
     */
    public List<Project> queryProjects(Project project);

    /**
     * 新增项目信息
     * @param project
     * @return
     */
    public int saveProject(Project project);
}
