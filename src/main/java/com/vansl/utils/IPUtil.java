package com.vansl.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author: vansl
 * @create: 18-4-24 下午7:04
 */
public class IPUtil {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            /*局域网无法获取公网ip，可使用http请求ip接口以获取
            if(ip.equals("127.0.0.1")||ip.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
            */
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        if (ip.isEmpty()){
            ip="未知";
        }
        return ip;
    }

    public static String getAddress(String ip){
        String address=new String();
        try {
            //请求api获取地址
            byte[] response= HttpUtil.doGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            //利用JSON转换unicode字符串
            address=JSON.parseObject(new String(response)).toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }
}