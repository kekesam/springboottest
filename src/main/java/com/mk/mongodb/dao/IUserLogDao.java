package com.mk.mongodb.dao;

import com.mk.mongodb.bean.UserLog;

import java.util.List;

public interface IUserLogDao extends  IBaseDao{

    //保存日志
    public UserLog save(UserLog userLog);
    //保存日志
    public UserLog getById(String id);
    //保存日志
    public List<UserLog> findUserLogs();
    //求总和
    public int countUserLogs();
    //保存日志
    public void update(UserLog userLog);
    //删除日志
    public void delete(String id);

    //todo 分页和模糊搜索，聚合函数
}
