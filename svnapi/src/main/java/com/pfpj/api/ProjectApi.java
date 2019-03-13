package com.pfpj.api;

import com.pfpj.entity.Project;
import com.pfpj.vo.ResultStatus;

/**
 * svn项目管理接口
 */
public interface ProjectApi {

    /**
     *新增svn项目
     *@param projectName  svn项目名称
     *@param projectPath  svn项目所在服务器绝对路径
     *@param projectUrl  svn项目的请求路径
     *@param projectMsg  svn项目描述信息
     * @return
     */

    public ResultStatus addProject( Project project);

}
