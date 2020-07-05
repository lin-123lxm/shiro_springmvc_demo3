package com.huikedu.vo;

import java.io.Serializable;

public class AdminUserVO implements Serializable {

    /*
    用来封装登录页面的表单中的登录名和密码
     */
    private  String username ;
    private  String password;
    private  boolean rememberMe;//记住我


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
