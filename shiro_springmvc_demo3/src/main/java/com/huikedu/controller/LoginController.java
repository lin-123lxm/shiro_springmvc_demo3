package com.huikedu.controller;


import com.huikedu.vo.AdminUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return  "login";
    }

    @PostMapping("/login")
    public String dologin(AdminUserVO adminUser) {
        try{
            //创建主体对象
            Subject subject = SecurityUtils.getSubject();
            if (adminUser != null) {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(adminUser.getUsername(), adminUser.getPassword());
                //登录认证
                subject.login(usernamePasswordToken);
                //是否记住我
                if (adminUser.isRememberMe()){
                    usernamePasswordToken.setRememberMe(true);
                }
            }
        }catch (UnknownAccountException ex){
            System.out.println("输入的账号不存在");
            return  "login";

        }catch (IncorrectCredentialsException ex){
            System.out.println("输入用户名密码不正确，请重新输入");
            return  "login";
        }
        return "index";
    }

    @GetMapping("/logout")
    public  String exit(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return  "login";
    }

    @RequestMapping("/perms/error")
    public String permsError(){
    //无权限的跳转
        return "perm_error";
    }

    //ajax的登录方法
    @PostMapping("/doLoginSubmit.do")
    @ResponseBody
    public  Map<String,Object> dosubmitLogin(AdminUserVO adminUserVo){
        Map<String, Object> resultMap  = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(adminUserVo.getUsername(),adminUserVo.getPassword());
        try{
            subject.login(token);
            if (adminUserVo.isRememberMe() == true){
                token.setRememberMe(true);
            }
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功！");
        } catch (DisabledAccountException dax) {
            resultMap.put("status", 500);
            resultMap.put("message", "帐号已被禁用！");
        }  catch (AccountException ae) {
            resultMap.put("status", 500);
            resultMap.put("message", "帐号或密码错误！");
        } catch (AuthenticationException ae) {
            resultMap.put("status", 500);
            resultMap.put("message", "身份认证失败！");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "登录认证错误！");
        }
        return resultMap;
    }




}
