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

    <link rel="icon" sizes="any" mask="" href="https://vanslblog.oss-cn-shanghai.aliyuncs.com/key_v.png">
    <link rel="stylesheet" href="/css/article.css">

</head>
<body  style="height: 100%">

<div class="inner">
    <h2 id="article-title">${blogData.title}</h2>
    <p id="article-time" align="right">
        <fmt:formatDate value="${blogData.time}" type="date" pattern="yyyy/MM/dd" />
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
        <label>称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;呼<span style="color:red">*</span></label><input id="name" />
    <label>联系方式<span style="color:red">*</span></label><input id="contact" />
        <label>评论内容<span style="color:red">*</span></label>
        <textarea id="comment-content"></textarea>
        <button id="submit">提交</button>
</div>
</body>

<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/layui/layui.js"></script>
<script src="/js/article.js"></script>
</html>
