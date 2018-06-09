<%--
  Created by IntelliJ IDEA.
  User: vansl
  Date: 18-3-19
  Time: 下午5:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
    <head>
        <title>遍历文件目录</title>
        <link rel="icon" sizes="any" mask="" href="https://vanslblog.oss-cn-shanghai.aliyuncs.com/key_v.png">
    </head>
<body>
<script src="https://qiyukf.com/script/ba9ea664ca869f7bc32b038c719d3657.js"></script>
<%!
    public   void listDirectory(String directory,HttpServletRequest request,JspWriter out) throws  Exception {
        File dir = new File(directory);
        if(dir.isFile())            //判断是否是文件，如果是文件则返回。
            return;
        File[] files=dir.listFiles();        //列出当前目录下的所有文件和目录
        for(int i=0;i<files.length;i++){
            if(files[i].isDirectory()) {    //如果是目录，则继续遍历该目录
                //listDirectory("/home/vansl/share/"+ files[i].getName(), request, out);
                //String url=urlEncode(directory.substring(12),out);
                //out.println( "<a href=/"+url+">"+files[i].getName()+"</a></br>");    //输出该目录或者文件的名字
                continue;
            }
            //String url= URLEncoder.encode(directory.substring(12) + "/" + files[i].getName(), "utf-8");
            out.println( "<a href=/"+directory.substring(12)+"/"+files[i].getName().replaceAll("\\s","%20")+">"+files[i].getName()+"</a></br>");    //输出该目录或者文件的名字
        }
    }

    /*
    public String urlEncode(String sourceStr,JspWriter out) throws Exception{
        String[] encodeTable = new String[2^8];
        for(int i=0;i<256;i++) {
            if(i>='0' && i<='9' || i>='a'&&i<='z' || i>='A' && i<='Z' || i=='-' || i=='_' || i=='.'){
                encodeTable[i] = (char)i + "";
            }else{
                out.println("%" + String.format("%02x",i).toUpperCase());
                encodeTable[i] = "%" + String.format("%02x",i).toUpperCase();
            }
        }
        final StringBuilder sb = new StringBuilder();
        for(int i=0;i<sourceStr.length();i++) {
            sb.append(encodeTable[sourceStr.charAt(i) & 0xFF]);
        }
        return sb.toString();
    }
    */
%>
<%
    //将当前web程序目录结构输出到控制台
    out.println("--------------------------------------------------------------------------------------------------------------------------------</br>");
    listDirectory("/home/vansl/"+request.getAttribute("beginDir"),request,out);
    out.println("</br>--------------------------------------------------------------------------------------------------------------------------------");
%>
</body>
</html>