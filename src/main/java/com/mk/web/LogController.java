package com.mk.web;

import com.mk.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogController {

    //创建一个日志类
    //private Logger log = LoggerFactory.getLogger(LogController.class);

    //lombok 除了能够自动生成 setter getter c
    //自动提供了日志支持。支持两种log4j+logback

    //lombok不是你在pom.xml文件中引入一个依赖的操作。

    private IUserService userService;
    @GetMapping("/log")
    public String log(){
        //todo  今天开发到这个为明天继续
        log.error("当前出错的类是：{}方式是:{} 参数是:{} 异常信息是:{}",LogController.class,"log()","name age");
        return "success";
    }

}
