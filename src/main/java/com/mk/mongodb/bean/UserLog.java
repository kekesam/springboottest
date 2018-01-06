package com.mk.mongodb.bean;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class UserLog {

    @Id
    private String id;
    //日志信息
    private String message;
    //时间，尽量使用字符串,可以date
    private String createTime;
    //更新时间
    private Date updateTime;
    //用户ID
    private Integer userId;
    //用户名
    private String username;
    //访问模块 1首页，2：详情页 3:登陆 4：
    private Integer model;
    //详细信息
    private String info;
    //ip地址
    private String ip;
    //操作系统
    private String os;
    //浏览器版本
    private String bos;

    public UserLog(){

    }

    public UserLog(String id, String message, String createTime, Date updateTime, Integer userId, String username, Integer model, String info, String ip, String os, String bos) {
        this.id = id;
        this.message = message;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.username = username;
        this.model = model;
        this.info = info;
        this.ip = ip;
        this.os = os;
        this.bos = bos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBos() {
        return bos;
    }

    public void setBos(String bos) {
        this.bos = bos;
    }
}
