package com.mk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;

@Controller
public class IndexController {


    @GetMapping("/index")
    public String index(){
        System.out.println("进来了吗");
        return "index";
    }

    @GetMapping("/index2")
    public ModelAndView index2(){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("进来了吗");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
