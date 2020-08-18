package com.scaudachuang.campus_navigation_unfx.POJO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResult {
    private int status;
    private int uid;
    private String msg;
    private String timestamp;

    public LoginResult(int status,String msg,String timestamp){
        this.msg = msg;
        this.status = status;
        this.timestamp = timestamp;
    }
    public LoginResult(int status,String msg,String timestamp,int uid){
        this.msg = msg;
        this.status = status;
        this.timestamp = timestamp;
        this.uid = uid;
    }
}
