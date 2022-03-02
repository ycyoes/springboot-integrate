package com.turing.springbootintegrate.model;

import java.util.Date;

public class ZyUserDHRel {
    private String userId;
    private String zyId;
    private String duanHao;
    private String loginName;
    private Date createTime;
    private String createUser;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZyId() {
        return zyId;
    }

    public void setZyId(String zyId) {
        this.zyId = zyId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDuanHao() {
        return duanHao;
    }

    public void setDuanHao(String duanHao) {
        this.duanHao = duanHao;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
