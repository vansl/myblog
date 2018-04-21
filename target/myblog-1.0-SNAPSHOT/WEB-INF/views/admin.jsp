<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-3-25
  Time: 下午5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>博客后台管理</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <style>
        .layui-layout-admin .layui-header {
            background-color: #4E5465;
        }
        .layui-logo img{
            height:40px;
            margin-right: 10px;
        }
        .layui-layout-left{
            padding: 0;
        }
        #searchInput,#searchScope,#searchBtn{
            margin-left:30px;
        }
        .layui-table-body{
            overflow: hidden;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">

        <div class="layui-logo"><img src="/images/config_ico.svg" />后台管理系统</div>

        <!-- 水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item" lay-unselect="" id="switchNav">
                <a href="javascript:;"><i class="layui-icon">&#xe65a;</i></a>
            </li>
            <li class="layui-nav-item"  lay-unselect="" id="searchInput">
                <input class="layui-input" name="search" autocomplete="off">
            </li>
            <li class="layui-nav-item" id="searchScope">
                <a href="javascript:;">文章</a>
                <dl class="layui-nav-child layui-anim layui-anim-upbit">
                    <dd><a href="">评论</a></dd>
                    <dd><a href="">分类</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-unselect="" id="searchBtn">
               <button  class="layui-btn search-btn"><i class="layui-icon">&#xe615;</i>搜索</button>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;" id="username">
                    <!--<img src="http://t.cn/RCzsdCq" class="layui-nav-img">-->
                    vansl
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">注销</a></li>

        </ul>
    </div>

    <div class="layui-side layui-bg-black">

        <div class="layui-side-scroll">
            <!-- 左侧导航 -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">博客管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="load('create_blog')">写博客</a></dd>
                        <dd class="layui-this"><a href="javascript:;" onclick="load('blog_admin')">文章管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed"><a href="javascript:;" onclick="load('comment_admin')">评论管理</a></li>
                <li class="layui-nav-item layui-nav-itemed"><a href="javascript:;" onclick="load('type_admin')">个人分类管理</a></li>
            </ul>
        </div>

    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        Copyright © 2018 myblog
    </div>
</div>
<script src="/layui/layui.js"></script>
<script src="/js/admin.js"></script>

</body>
</html>