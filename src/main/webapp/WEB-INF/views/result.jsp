<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传结果</title>
    <link type="text/css" rel="styleSheet"  href="/css/a.css" />
    <style>
        a:link {
            font-size: 25px;
            color: #000000;
            text-decoration: none;
        }
        a:visited {
            font-size: 25px;
            color: #000000;
            text-decoration: none;
        }
        a:hover {
            font-size: 25px;
            color: #999999;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${requestScope.uploadTime == null}">
        文件上传失败！原因：
        <c:out value="${requestScope.error}"/>
    </c:when>
    <c:otherwise>
        文件上传成功！</br>
        文件大小：<c:out value="${requestScope.size}" />MB</br>
        上传耗时：<c:out value="${requestScope.uploadTime}" />秒
        </br>
        <a href="/files/share/">点此查看所有文件</a>
    </c:otherwise>
</c:choose>
</body>
</html>