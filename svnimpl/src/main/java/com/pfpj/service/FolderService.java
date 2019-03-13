package com.pfpj.service;

import com.pfpj.entity.Folder;
import com.pfpj.vo.ResultStatus;

public interface FolderService {
    ResultStatus addFolder(Folder folder,String folderUrl);
}
