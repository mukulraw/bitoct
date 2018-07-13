
package com.sadak.bitcoin.model.Login;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("RoleType")
    @Expose
    private String roleType;
    @SerializedName("MemberId")
    @Expose
    private String memberId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("LastLogin")
    @Expose
    private String lastLogin;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    private final static long serialVersionUID = -3954414967297253031L;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

}
