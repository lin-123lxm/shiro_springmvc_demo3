package com.huikedu.resolver;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ex.printStackTrace();//开发时必需
        ModelAndView mv = new ModelAndView();
        //当适配到shior的认证异常时，可以指定跳转的页面。
        if(ex instanceof IncorrectCredentialsException || ex instanceof UnknownAccountException){
            //跳转登录页面，重新登录
            mv.setViewName("redirect:/user/login");
        }
        if (ex instanceof  AuthenticationException){
            mv.setViewName("redirect:/user/perms/error");
        }
        if (ex instanceof UnauthorizedException){

            mv.setViewName("redirect:/user/perms/error");

        }
        return mv;
    }
}
