<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-3-17
  Time: 下午10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<form name="form" action="<c:url value='/fileUpload'/>" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <br/>
    <br/>
    <input type="submit" value=">点击上传文件"/>
    <p>你的ip：<%=request.getRemoteAddr()%></p>
</form>
</body>
</html>
