<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-5-5
  Time: 上午11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>${blogData.title}</title>
    <style>
        body {
            font-family: "lucida grande", "lucida sans unicode", lucida, helvetica, "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif;
            font-size: 18px;
        }
        @media (max-width: 1000px) {
            body {
                font-size:48px;
            }
        }
        #article-title{
            font-family: "Palatino Linotype", "Book Antiqua", Palatino, Helvetica, STKaiti, SimSun, serif;
            padding: 5px;
            border-bottom: 2px LightGrey solid;
            width: 98%;
            line-height: 150%;
            color: #666666;
        }
        div.inner {
            margin: 0% 14%;
            padding: 2% 8% 4% 8%;
            border: 1px solid LightGrey;
        }

        @media (max-width: 1000px) {
            div.inner {
                margin: 0% 2%;
                padding: 1% 4% 2% 4%;
            }
        }

        div.comment-list>div.comment{
            margin: 2% 14%;
            border: 1px solid LightGrey;
            height:auto!important;
        }
        strong{
            display:block;
        }
        @media (max-width: 1000px) {
            div.comment-list>div.comment{
                font-size: 38px;
                margin: 1% 2%;
            }
        }

        div.submit_commit {
            margin: 2% 14%;
            display: grid;
            grid-row-gap:10px;
            grid-template-rows: 30px 30px 80px 30px;
            grid-template-columns: 120px 400px;
        }

        @media (max-width: 1000px) {
            div.submit_commit {
                margin: 1% 2%;
                grid-template-rows: 60px 60px 160px 60px;
                grid-template-columns: 30% 60%;
                font-size: 38px;
            }
        }

        #comment-content{
            overflow: hidden;
        }

    </style>
</head>
<body>

<div class="inner">
    <h2 id="article-title">${blogData.title}</h2>

    <p id="article-time" align="right">
        <fmt:formatDate value="${blogData.time}" pattern="yyyy/MM/dd" />
    </p>

    <div id="article-content">${blogContent}</div>
</div>

<div class="comment-list">
    <c:forEach var = "comment" items="${comments}" begin="0">
        <div class="comment">
            <strong>${comment.name}(${comment.contact}):</strong>
            <p>
                ${comment.content}
            </p>
            <strong class="comment-info" style="font-weight:bold;">
                <fmt:formatDate value="${comment.time}" type="date" pattern="yyyy/MM/dd HH:mm:ss" />
                ${comment.address}
            </strong>
        </div>
    </c:forEach>
</div>

<div class="submit_commit">
        <label>称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;呼*</label><input id="name" />
        <label>联系方式*</label><input id="contact" />
        <label>评论内容*</label>
        <textarea id="comment-content"></textarea>
        <button id="submit">提交</button>
</div>
</body>

<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/layui/layui.js"></script>

<script type="text/javascript">
    var jq= jQuery.noConflict();

    function parseTime(time){
        var date = new Date(time);//如果date为13位不需要乘1000
        var Y = date.getFullYear() + '/';
        var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '/';
        var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
        return Y+M+D;
    }
    jq("#article-time").text(parseTime("${blogData.time}"));

    jq("#submit").click(function () {
        if(!jq('#name').val()){
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.open({
                    title: '在线调试'
                    ,content: '可以填写任意的layer代码'
                });

            });
            return;
        }
        jq.ajax({
            url: "/comment/",
            type: "post",
            data:JSON.stringify({
                "name":jq('#name').val(),
                "contact":jq('#contact').val(),
                "content":jq('#comment-content').val(),
                "blogId":window.location.href.split("/")[4],
            }),
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                setTimeout(function () {
                    window.location.reload();
                },3000);
            }
        });
    });
</script>
</html>
