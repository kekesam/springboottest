package com.mk.dao;

import com.mk.bean.User;
import org.apache.ibatis.annotations.Param;

public interface IUserMapper {

    public User login(@Param("account") String account,
                      @Param("password")String password);

}
