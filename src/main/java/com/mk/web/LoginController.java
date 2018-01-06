package com.mk.web;

import com.mk.bean.User;
import com.mk.commons.Const;
import com.mk.commons.DataInfo;
import com.mk.service.IUserService;
import com.mk.util.JsonUtil;
import com.mk.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    /*@ResponseBody
    @PostMapping("/logined")
    public DataInfo<User> logined(User user, HttpSession session){
        DataInfo<User> dataInfo = userService.login(user.getEmail(),user.getPassword());
        //放入session
        session.setAttribute("session_user",dataInfo.getData());
        return dataInfo;
    }*/


    @ResponseBody
    @PostMapping("/logined")
    public DataInfo<User> logined(User user){
        DataInfo<User> dataInfo = userService.login(user.getEmail(),user.getPassword());
        if(dataInfo.isSuccess()){
            //放入redis中
            RedisUtil.setex(Const.REDIS_KEYS_NAME, JsonUtil.obj2String(user),1800);
        }
        return dataInfo;
    }


    @ResponseBody
    @GetMapping("/getUserInfo")
    public DataInfo<User> getUserInfo(){
        return userService.getUserInfo(Const.REDIS_KEYS_NAME);
    }
}
