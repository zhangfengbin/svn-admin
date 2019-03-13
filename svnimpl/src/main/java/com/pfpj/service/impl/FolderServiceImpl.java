package com.pfpj.service.impl;

import com.pfpj.dao.FolderMapper;
import com.pfpj.entity.Folder;
import com.pfpj.service.FolderService;
import com.pfpj.utils.SvnUtils;
import com.pfpj.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tmatesoft.svn.core.SVNException;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    public FolderMapper folderMapper ;
    @Autowired
    public SvnUtils svnUtils;

    @Override
    @Transactional
    public ResultStatus addFolder(Folder folder,String folderUrl) {
        List<Folder> folders = folderMapper.queryFolderByFolderPath(folder.getFolderPath());
        if(folders.size()>0){
            return ResultStatus.resultFailed(2,"文件路径已存在，无法新增");
        }else{
               try {
                   //TODO 操作svn 创建文件夹 并在当前文件夹下创建doc 和code 文件夹
                   saveFolderAndChildrenFolder(folder);
               } catch (Exception e) {
                   e.printStackTrace();
                  return  ResultStatus.resultFailed(2,"svn文件夹操作失败");
               }
              return ResultStatus.resultOk(folderUrl);

        }

    }

    /**
     * 保存文件信息到数据库 并操作svn新增文件信息
     * @param folder
     */
    public void saveFolderAndChildrenFolder(Folder folder) throws SVNException {

        String folderPath = folder.getFolderPath();

        folderMapper.saveFolder(folder);
//        svnUtils.imoprtToSvnDir(null, null);
        svnUtils.addNewSvnDir(folder.getFolderPath(),folder.getFolderName());

        folder.setFolderName("code");
        folder.setFolderPath(folderPath+"/code");
        folderMapper.saveFolder(folder);
        svnUtils.addNewSvnDir(folder.getFolderPath(),folder.getFolderName());
//
        folder.setFolderName("doc");
        folder.setFolderPath(folderPath+"/doc");
        folderMapper.saveFolder(folder);
        svnUtils.addNewSvnDir(folder.getFolderPath(),folder.getFolderName());
    }


}
