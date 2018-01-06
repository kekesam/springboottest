package com.mk.service;

import com.mk.bean.User;
import com.mk.commons.DataInfo;

public interface IUserService {

    /**
     * 根据邮箱和密码查询用户信息
     * @param email
     * @param password
     * @return
     */
    public DataInfo<User> login(String email, String password);

    public DataInfo<User> getUserInfo(String key);
}
