<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="${contextPath}/css/common.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">

    <form method="POST" action="" class="form-signin">
        <h2 class="form-heading">权限管理系统</h2>
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" id="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" id="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <label for="rememberMe" class="checkbox-inline"   >
                <input name="rememberMe" id="rememberMe" type="checkbox"/>
                记住我
            </label>
            <span>${error}</span>

            <button class="btn btn-lg btn-primary btn-block" type="button" id="login">登录</button>
            <h4 class="text-center"><a href="${contextPath}/regist">Create an account</a></h4>
        </div>

    </form>

</div>
<script type="text/javascript" src="js/common/layer/layer.js"></script>
<script >
    jQuery(document).ready(function() {
        //回车事件绑定
        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){
                $('#login').click();
            }
        };

        //登录操作
        $('#login').click(function(){

            var username = $('#username').val();
            var password = $('#password').val();
            alert(username+","+password);
            if(username == '') {
                layer.msg('用户名不能为空！');
                return false;
            }
            if(password == '') {
                layer.msg("密码不能为空！");
                return false;
            }
            var data = {"username":username,"password":password,"rememberMe":$("#rememberMe").is(':checked')};
            var load = layer.load();

            $.ajax({
                url:"doLoginSubmit.do",
                data:data,
                type:"post",
                dataType:"json",
                beforeSend:function(){
                    layer.msg('开始登录...');
                },
                success:function(result){
                    layer.close(load);
                    if(result && result.status != 200){
                        layer.msg(result.message,function(){});
                        $('.password').val('');
                        return;
                    }else{
                        layer.msg(result.message);
                        setTimeout(function(){
                            //登录返回
                            window.location.href='<c:out value="${contextPath}"/>/';
                        },1000)
                    }
                },
                error:function(e){
                    console.log(e,e.message);
                    layer.msg('登录异常！',new Function());
                }
            });
        });


    });
</script>

</body>
</html>
