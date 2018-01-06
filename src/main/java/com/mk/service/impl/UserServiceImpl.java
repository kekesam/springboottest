package com.mk.service.impl;

import com.mk.bean.User;
import com.mk.commons.Const;
import com.mk.commons.DataInfo;
import com.mk.dao.UserMapper;
import com.mk.service.IUserService;
import com.mk.util.JsonUtil;
import com.mk.util.RedisUtil;
import com.mk.web.LogController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{


    //创建一个日志类
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public DataInfo<User> login(String email, String password) {
        User user = userMapper.login(email,password);
        if(user==null) {
            return DataInfo.createError("登录失败");
        }
        return DataInfo.createSuccess("登录成功",user);
    }


    //从redis获取
    public DataInfo<User> getUserInfo(String key){
        String jsonString = RedisUtil.get(key);
        return DataInfo.createSuccess(JsonUtil.string2Obj(jsonString,User.class));
    }
}
