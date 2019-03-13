package com.pfpj.exception;

import org.springframework.http.HttpStatus;

public enum  ExceptionEnum {
    SVN_USER_ERROR(10001, "操作用户失败",HttpStatus.BAD_REQUEST),
    SVN_FOLDER_ADD_ERROR(10002, "文件夹添加失败",HttpStatus.BAD_REQUEST),
    SVN_FOLDER_ERROR(10003, "文件写入失败",HttpStatus.BAD_REQUEST),
    SVN_AUTHORITY_ERROR(10004, "文件夹权限一致,无需改变",HttpStatus.BAD_REQUEST),
    SVN_AUTHORITY_USERNAME_ERROR(10005, "没有当前用户信息",HttpStatus.BAD_REQUEST),
    SVN_AUTHORITY_FOLDERPATH_ERROR(10006, "文件路径不存在",HttpStatus.BAD_REQUEST),
    SVN_AUTHORITY_DELETE_ERROR(10007, "删除用户失败",HttpStatus.BAD_REQUEST);

    private int code;
    private String msg;
    private HttpStatus httpStatus;

    ExceptionEnum(int code, String msg, HttpStatus status) {
        this.code = code;
        this.msg = msg;
        this.httpStatus = status;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
