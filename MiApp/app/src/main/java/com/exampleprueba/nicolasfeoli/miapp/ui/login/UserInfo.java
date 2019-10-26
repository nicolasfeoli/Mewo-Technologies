
package com.exampleprueba.nicolasfeoli.miapp.ui.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("givenName")
    @Expose
    private String givenName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public UserInfo withUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public UserInfo withGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserInfo withRole(String role) {
        this.role = role;
        return this;
    }

}
