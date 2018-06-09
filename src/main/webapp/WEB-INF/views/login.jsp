<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-3-24
  Time: 下午12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="icon" sizes="any" mask="" href="https://vanslblog.oss-cn-shanghai.aliyuncs.com/key_v.png" />
    <link rel="stylesheet" href="/layui/css/layui.css" media="all" />
    <style>

        html,body{
            height: 100%;
            margin: 0 auto;
            padding: 0;
            position: relative;
            background-image: url("/images/login_bg2.jpg");
            background-size: cover;
        }

        .wrapper{
            position: absolute;
            left:0;
            top: 0;
            bottom: 0;
            right: 0;
            margin: auto;
            width: 500px;
            height: 300px;
        }

        .head img{
            position: absolute;
            left:0;
            right: 0;
            margin: auto;
            height: 50px;
        }

        .main{
            position: relative;
            top: 60px;
            height: 240px;
        }

        .layui-form-item{
            position: relative;
            text-align: center;
            margin-top: 15px;
            left: 20%;
            width: 60%;
        }

        input{
            color: white ;
            background: none !important;
        }

        .captcha>img{
            float: right;
            width: 40%;
            height: 38px;
            cursor:pointer;
        }

        .captcha>input{
            width: 60%;
        }
    </style>
</head>

<body >
<div class="wrapper">

    <div class="head">
        <img src="/images/login_ico.png" />
    </div>

    <div class="main">
        <!--<fieldset>-->
        <form class="layui-form" name="loginForm">
            <div class="layui-form-item">
                <input class="layui-input" type="text" name="userName" autocomplete="off" placeholder="用户名" lay-verify="require" />
            </div>
            <div class="layui-form-item">
                <input class="layui-input" type="password" name="password" autocomplete="off" placeholder="密码" lay-verify="require" />
            </div>
            <div class="layui-form-item captcha">
                <img src="/images/cap.png" onclick="this.src=''" />
                <input class="layui-input" type="text" name="captcha" autocomplete="off" placeholder="请输入验证码" lay-verify="require" />
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="go">登录</button>
            </div>
        </form>
        <!--</fieldset>-->
    </div>

</div>
</body>

<script type="text/javascript" src="/layui/layui.js"></script>

<script>
    layui.use(['layer', 'form'], function() {
        //加载所需模块
        var layer = layui.layer,
            form = layui.form,
            $ = layui.jquery;

        //表单验证
        form.verify({
            require:function(value,item){
                if(item.name=="userName"&&value==""){
                    return "请输入用户名";
                }else if(item.name=="password"&&value==""){
                    return "请输入密码";
                }else if(item.name=="captcha"&&value==""){
                    return "请输入验证码";
                }
            }
        });

        //ajax请求登录,登录成功则跳转到后台管理页面,否则刷新验证码
        form.on('submit(go)', function(data){
            $.ajax({
                url:"/check",
                contentType: "application/json; charset=utf-8",
                data:JSON.stringify(data.field),
                type: "post",
                success:function(result){
                    if(result==="success"){
                        location.replace("/admin");
                    }else{
                        if(result==="error"){
                            layer.alert('系统错误', {icon: 2});
                        }else if(result==="wrong"){
                            layer.alert('用户名或密码密码错误', {icon: 2});
                        }else if(result==="denied"){
                            layer.alert('权限不足', {icon: 2});
                        }else{
                            layer.alert(result, {icon: 2});
                        }
                        //请求新的验证码
                        $(".captcha img").attr("src","");
                    }
                }
            });
            return false;
        });
    });
</script>

</html>