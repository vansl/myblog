package com.vansl.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vansl.dao.BlogDao;
import com.vansl.dao.UserDao;
import com.vansl.utils.AliyunOSSUtil;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: vansl
 * @create: 18-5-15 下午5:54
 *
 * 处理博客更新时的图片变化
 */
public class BlogImgInterceptor implements HandlerInterceptor {

    @Autowired
    BlogDao blogDao;

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        //拦截updateBlog、deleteBlog两个请求
        if(request.getMethod().equals("PUT")||request.getMethod().equals("DELETE")){
            //从请求url中提取博客id
            String uri=request.getRequestURI();
            Integer blogId=Integer.valueOf(uri.substring(uri.lastIndexOf("/")+1));

            //身份验证
            Subject subject = SecurityUtils.getSubject();
            String userName=(String)subject.getPrincipals().getPrimaryPrincipal();
            Integer loginId=userDao.selectIdByUserName(userName);
            Integer userId=blogDao.selectUserIdByBlogId(blogId);
            if(userId!=loginId){
                return false;
            }

            //将请求体的json字符串转换为json对象
            JSONObject requestData=JSON.parseObject(IOUtils.toString(request.getReader()));
            //原博客内容
            String origin=blogDao.selectContentByBlogId(blogId);
            //提取原博客中的图片url
            HashSet<String> originUrl=getImageUrl(origin,"<img\\ssrc=\"(.*?oss.*?)\".*?>");

            //如果是删除操作则直接删除所有图片
            if(request.getMethod().equals("DELETE")){
                //遍历原博客的所有图片url
                for (String imgUrl:originUrl) {
                    String fileName=imgUrl.substring(imgUrl.lastIndexOf("/")+1);
                    AliyunOSSUtil.deleteFile("vanslblog",userId+"",fileName);
                }
            //否则如果是更新操作，对比之后删除
            }else{
                //提取现博客中的图片url
                HashSet<String> currentUrl=getImageUrl((String)requestData.get("content"),"<img\\ssrc=\"(.*?oss.*?)\".*?>");
                //遍历原博客的所有图片url
                for (String imgUrl:originUrl) {
                    //如果现博客不含该url则调用删除
                    if (!currentUrl.contains(imgUrl)){
                        String fileName=imgUrl.substring(imgUrl.lastIndexOf("/")+1);
                        AliyunOSSUtil.deleteFile("vanslblog",userId+"",fileName);
                    }
                }
            }
        }
        return true;
    }

    public HashSet<String> getImageUrl(String content,String regex){
        HashSet<String> result=new HashSet<>();
        try{
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                result.add(matcher.group(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
    }

}