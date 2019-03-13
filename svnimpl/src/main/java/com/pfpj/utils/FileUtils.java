package com.pfpj.utils;

import com.pfpj.entity.AuthorityUserAndFoler;
import com.pfpj.entity.SvnUser;
import com.pfpj.exception.ExceptionEnum;
import com.pfpj.exception.PFPJException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * svn 用户账号密码文件操作类 在文件里对用户信息进行操作
 */
public class FileUtils {
    private static final String SEP = System.getProperty("line.separator");
    public static final Logger logger= LoggerFactory.getLogger(FileUtils.class);


    /**
     * 更新authz文件
     * @param authorityUserAndFolerList 用户文件权限数据
     * @param projectName 项目名称
     * @param authzPath 项目所在路径
     * @return
     */
    public static boolean  saveOrUpdateAuthz(List<AuthorityUserAndFoler> authorityUserAndFolerList, String projectName, String authzPath){
        Map<String,List<AuthorityUserAndFoler>> map = new HashMap<String,List<AuthorityUserAndFoler>>();
        for(AuthorityUserAndFoler authorityUserAndFoler : authorityUserAndFolerList){
            //TODO 判断map集合是否有key 如果没有新增
            List<AuthorityUserAndFoler> authorityUserAndFolers =(List<AuthorityUserAndFoler>) map.get(authorityUserAndFoler.getFolderPath());
            if(authorityUserAndFolers == null ) {
                authorityUserAndFolers = new ArrayList<AuthorityUserAndFoler>();
           }
            authorityUserAndFolers.add(authorityUserAndFoler);
            map.put(authorityUserAndFoler.getFolderPath(),authorityUserAndFolers);
        }
        //TODO 拼装字符串 写入authz文件
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[aliases]").append(SEP);
        for (String key : map.keySet()) {
            stringBuffer.append("["+projectName+":"+key+"]").append(SEP);
            List<AuthorityUserAndFoler> authoritys = map.get(key);
            for (AuthorityUserAndFoler authorityUserAndFoler : authoritys){
                stringBuffer.append(authorityUserAndFoler.getSvnUserName()+"="+authorityUserAndFoler.getAuthority()).append(SEP);
            }
        }

        //调用写文件的方法
//        if(!projectPath.endsWith("/")){
//            projectPath=projectPath+"/";
//        }
        File file = new File(authzPath );
        if(file.exists()){
            write(file,stringBuffer.toString());
        }else{
            throw new PFPJException(
                    ExceptionEnum.SVN_FOLDER_ERROR.getHttpStatus(),
                    ExceptionEnum.SVN_FOLDER_ERROR.getCode(),
                    ExceptionEnum.SVN_FOLDER_ERROR.getMsg()
            );
        }


        return true;
    }

    /**
     * 保存用户的信息
     * @param svnUsersList 查询的用户信息
     * @param passwdPath  项目的path （在服务器项目所在的绝对路径）
     * @return
     */
    public static boolean  saveOrUpdateSvnUser(List<SvnUser> svnUsersList, String passwdPath){
//        if(!passwdPath.endsWith("/")){
//            projectPath=projectPath+"/";
//        }
        File file = new File(passwdPath);
        boolean exists = file.exists();
        if(exists){
            StringBuffer stringBuffer = new StringBuffer();
            for (SvnUser svnUser : svnUsersList){
                stringBuffer.append(svnUser.getSvnUserName()).append(":{SHA}").append(EncryptUtil.encriptSHA1(svnUser.getSvnUserPwd())).append(SEP);
            }
            write(file,stringBuffer.toString());
            return true;
        }else{
            logger.warn("file not found!");
            return false;
        }
    }
    private static void write(File outFile, String contents) {
        BufferedWriter writer = null;
        try {
            if (contents == null) {
                contents = "";
            }
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));// UTF-8 without
            // BOM
            writer.write(contents);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PFPJException(
                    ExceptionEnum.SVN_FOLDER_ERROR.getHttpStatus(),
                    ExceptionEnum.SVN_FOLDER_ERROR.getCode(),
                    ExceptionEnum.SVN_FOLDER_ERROR.getMsg()
            );
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new PFPJException(
                            ExceptionEnum.SVN_FOLDER_ERROR.getHttpStatus(),
                            ExceptionEnum.SVN_FOLDER_ERROR.getCode(),
                            ExceptionEnum.SVN_FOLDER_ERROR.getMsg()
                    );
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new PFPJException(
                            ExceptionEnum.SVN_FOLDER_ERROR.getHttpStatus(),
                            ExceptionEnum.SVN_FOLDER_ERROR.getCode(),
                            ExceptionEnum.SVN_FOLDER_ERROR.getMsg()
                    );
                }
            }
        }
    }



}
