<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-3-19
  Time: 下午5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.IOException"%>
<%@ page contentType="text/html;charset=GB2312" language="java" %>
<html>
    <head><title>遍历文件目录</title>
</head>
<body>
<%!
    public   void listDirectory(String directory,HttpServletRequest request,JspWriter out) throws  IOException {
        File dir = new File(directory);
        if(dir.isFile())            //判断是否是文件，如果是文件则返回。
            return;
        File [] files=dir.listFiles();        //列出当前目录下的所有文件和目录
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()) {    //如果是目录，则继续遍历该目录
                listDirectory(request.getServletContext().getRealPath("/WEB-INF/statics/share/") + files[i].getName(), request, out);
            }
            out.println( "<a href=\"/share/"+files[i].getName()+"\">"+files[i].getName()+"</a></br>");    //输出该目录或者文件的名字
        }
    }
%>
<%
    //将当前web程序目录结构输出到控制台
    out.println("--------------------------------------------------------------------------------------------------------------------------------</br>");
    listDirectory(request.getServletContext().getRealPath("/WEB-INF/statics/share/"),request,out);
    out.println("</br>--------------------------------------------------------------------------------------------------------------------------------");
%>
</body>
</html>