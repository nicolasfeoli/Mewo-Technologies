
package com.exampleprueba.nicolasfeoli.miapp.ui.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSession {

    @SerializedName("LoginData")
    @Expose
    private LoginData loginData;
    @SerializedName("UserInfo")
    @Expose
    private UserInfo userInfo;

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public UserSession withLoginData(LoginData loginData) {
        this.loginData = loginData;
        return this;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserSession withUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

}
