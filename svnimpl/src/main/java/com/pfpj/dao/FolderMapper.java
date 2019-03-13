package com.pfpj.dao;

import com.pfpj.entity.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FolderMapper {
    List<Folder> queryFolderByFolderPath(@Param("folderPath") String folderPath);

    int saveFolder(Folder folder);

}
