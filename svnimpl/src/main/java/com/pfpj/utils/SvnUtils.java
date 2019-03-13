package com.pfpj.utils;

import com.pfpj.conf.ProjectConfigure;
import com.pfpj.exception.ExceptionEnum;
import com.pfpj.exception.PFPJException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.File;


@Component
public class SvnUtils {
   @Autowired
   public ProjectConfigure projectConfigure;

    public final Logger logger= LoggerFactory.getLogger(getClass());


    /**
     * @Description: 获取SVNRepository
     * @param url
     */
    public  SVNRepository getSVNRepository(String url) throws SVNException {

        SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        ISVNAuthenticationManager authManager =  SVNWCUtil.createDefaultAuthenticationManager( projectConfigure.getUserName() , projectConfigure.getPasswd() );
        repository.setAuthenticationManager( authManager );

        return repository;
    }

    /**
     * @Description: 获取SVNRepository
     * @param
     */
    public  SVNRepository getSVNRepository() throws SVNException{
        return getSVNRepository(projectConfigure.getProjectUrl());
    }


    /**
     * @Description: 获取 SVNUpdateClient
     * @param
     */
    public  SVNUpdateClient getSVNUpdateClient(){

        //声明SVN客户端管理类
        SVNClientManager ourClientManager = null;

        //初始化库。 必须先执行此操作。具体操作封装在setupLibrary方法中。
        setupLibrary();



        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        //实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance(
                (DefaultSVNOptions) options, projectConfigure.getUserName(), projectConfigure.getPasswd());

        //通过客户端管理类获得updateClient类的实例。
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();

        updateClient.setIgnoreExternals(false);

        return updateClient;

    }

    /*
     * 初始化库
     */
    private  void setupLibrary() {
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        /*
         * For using over svn:// and svn+xxx://
         */
        SVNRepositoryFactoryImpl.setup();

        /*
         * For using over file:///
         */
        FSRepositoryFactory.setup();
    }

    /**
     * @Description: 获取 SVNClientManager
     * @param
     */
    public  SVNClientManager getSVNClientManager(){
        DAVRepositoryFactory.setup();
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        //实例化客户端管理类
        return SVNClientManager.newInstance((DefaultSVNOptions) options, projectConfigure.getUserName(), projectConfigure.getPasswd());
    }


    /**
     * 添加文件夹到svn
     * @param path
     * @param folderName
     * @throws SVNException
     */
    public  void addNewSvnDir(String path, String folderName) throws SVNException {

        String filename = path.substring(path.lastIndexOf("/") + 1);
        String url = projectConfigure.getProjectUrl() + "/" + path.substring(0,path.lastIndexOf("/"));
        SVNRepository repository = null;
        try{
            repository = getSVNRepository(url);
        }catch (SVNException e){
            logger.warn("相对路径错误，请检查后重试");
            throw new PFPJException(
                    ExceptionEnum.SVN_FOLDER_ADD_ERROR.getHttpStatus(),
                    ExceptionEnum.SVN_FOLDER_ADD_ERROR.getCode(),
                    ExceptionEnum.SVN_FOLDER_ADD_ERROR.getMsg()
            );
        }

        SVNNodeKind nodeKind = repository.checkPath(filename, -1);
        //获得版本库中文件的状态（是否存在），参数-1表示是版本库中的最新版本。
        ISVNEditor editor = repository.getCommitEditor("logMessage", null,true,null);
        if (nodeKind == SVNNodeKind.NONE){//如果文件夹不存在,创建一个
            editor.openRoot(-1);
            editor.addDir(filename,null, -1);
            editor.closeDir();
            editor.closeEdit();
        }else {
            throw new PFPJException(
                    ExceptionEnum.SVN_FOLDER_ADD_ERROR.getHttpStatus(),
                    ExceptionEnum.SVN_FOLDER_ADD_ERROR.getCode(),
                    ExceptionEnum.SVN_FOLDER_ADD_ERROR.getMsg()
            );
        }
    }

    public  void imoprtToSvnDir(File localFile, String url){

        SVNClientManager svnClientManager = getSVNClientManager();
        //执行导入操作
        SVNCommitInfo commitInfo = null;
        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded(projectConfigure.getProjectUrl());
            File file = new File("F:\\test1");
            commitInfo = svnClientManager.getCommitClient().doImport(file, repositoryURL,
                    "import operation!",null, false,false, SVNDepth.INFINITY);
        } catch (SVNException e) {
           e.printStackTrace();
        }
    }

}
