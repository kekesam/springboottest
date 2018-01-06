package com.mk.commons;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mk.bean.User;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DataInfo<T> {

    private Integer status;
    private String message;
    private T data;


    private DataInfo(Integer status){
        this.status = status;
    }
    private DataInfo(Integer status,T data){
        this.status = status;
        this.data = data;
    }

    private DataInfo(Integer status,String message,T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private DataInfo(Integer status,String message){
        this.status = status;
        this.message = message;
    }


    public Integer getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }


    //暴露一系列的公开静态方法 0 error
    public static <T> DataInfo<T> createError(){
        return new DataInfo<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> DataInfo<T> createError(String message){
        return new DataInfo<T>(ResponseCode.ERROR.getCode(),message);
    }

    /**
     * 可能会存在状态很多的情况下，比如用名为空 1 密码错误 2 邮箱重复是 3。。。。。
     * @param status
     * @param message
     * @param <T>
     * @return
     */
    public static <T> DataInfo<T> createError(Integer status,String message){
        return new DataInfo<T>(status,message);
    }


    //暴露一系列的公开静态方法 0 error
    public static <T> DataInfo<T> createSuccess(){
        return new DataInfo<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
    }

    /**
     * 可能会存在状态很多的情况下，比如用名为空 1 密码错误 2 邮箱重复是 3。。。。。
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> DataInfo<T> createSuccess(T data){
        return new DataInfo<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(),data);
    }


    //暴露一系列的公开静态方法
    public static <T> DataInfo<T> createSuccess(String message){
        return new DataInfo<T>(ResponseCode.SUCCESS.getCode(),message);
    }

    /**
     * 可能会存在状态很多的情况下，比如用名为空 1 密码错误 2 邮箱重复是 3。。。。。
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> DataInfo<T> createSuccess(String message,T data){
        return new DataInfo<T>(ResponseCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 可能会存在状态很多的情况下，比如用名为空 1 密码错误 2 邮箱重复是 3。。。。。
     * @param status
     * @param message
     * @param <T>
     * @return
     */
    public static <T> DataInfo<T> createSuccess(Integer status,String message){
        return new DataInfo<T>(status,message);
    }


    //todo 到时候讲！！！！
    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }
}
