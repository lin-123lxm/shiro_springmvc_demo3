package com.huikedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexpageController {

    @RequestMapping("/")
    public  String index(){
        // 如果跳转index.jsp页面之前的统计的数据查询，可以写在此处。
        return "index";
    }

    @RequestMapping("/welcome.do")
    public String welcome(){
        return  "welcome";
    }



}
