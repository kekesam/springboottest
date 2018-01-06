package com.mk.commons;

/**
 * Created by xuke
 * 总结：枚举类是一个java类。定义的枚举方法都会调用枚举的构造函数
 * 并且方法这个枚举的对象。
 */
public enum ResponseCode {

    SUCCESS(1,"SUCCESS"),
    ERROR(0,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
