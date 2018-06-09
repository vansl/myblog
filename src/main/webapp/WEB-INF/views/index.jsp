<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-4-30
  Time: 下午12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>vansl-个人网站-${welcome}</title>
    <link rel="icon" sizes="any" mask="" href="https://vanslblog.oss-cn-shanghai.aliyuncs.com/key_v.png" />
    <link rel="stylesheet" href="/css/index.css">
    <script src="/layui/layui.js"></script>
    <script src="/js/jquery-3.3.1.min.js"></script>
</head>

<body>
    <nav class="top">
        <div class="navbar-brand">代码与漫谈</div>
        <div class="navbar-module">
            <ul>
                <li class="active">
                    <a href="/#">博客</a>
                </li>
                <li>
                    <a href="/others">其他</a>
                </li>
                <li>
                    <a href="/login">管理</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="content">

        <div class="category"></div>

        <div class="article">
            <ul class="article-list">

                <c:forEach var = "article" items="${articleList}" begin="0">
                    <li class="article-list-item">
                        <a href="/article/${article.id}">${article.title}</a>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>
</body>

<script type="text/javascript">
    var jq= jQuery.noConflict();

    //菜单列表html
    var menu = '';

    function BuildTree(data) {
        if (data&&data.length> 0) {
            menu += '<ul>';
            for (var i in data) {
                menu+= '<li><a href="/article?userId=1&typeId='+data[i].id+'">'+data[i].text+'</a>';
                //如果有子节点则加上一层ul实现缩进
                if(data[i].children){
                    BuildTree(data[i].children);
                }
                menu+= '</li>';
            }
            menu += '</ul>';
        }

    }

    jq(function () {
        BuildTree(${typeData});
        jq(".category").append(menu);

    });

</script>

</html>
