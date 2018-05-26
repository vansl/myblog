package com.vansl.shiro;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author: vansl
 * @create: 18-5-21 下午4:11
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

    //session有效期3天,单位为毫秒
    private static Integer expireTime=3 * 24 * 60 * 60 * 1000;
    /*
    @Override
        public Serializable getSessionId(SessionKey key) {
        Serializable sessionId = key.getSessionId();
        if(sessionId == null){
            HttpServletRequest request = WebUtils.getHttpRequest(key);
            HttpServletResponse response = WebUtils.getHttpResponse(key);
            sessionId = this.getSessionId(request,response);
        }
        HttpServletRequest request = WebUtils.getHttpRequest(key);
        request.setAttribute(TOKEN_NAME,sessionId.toString());
        return sessionId;
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = ((HttpServletRequest)request).getHeader(HEADER_TOKEN_NAME);
        if(token == null){
            token = UUID.randomUUID().toString();
        }

        //这段代码还没有去查看其作用，但是这是其父类中所拥有的代码，重写完后我复制了过来...开始
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());
        //这段代码还没有去查看其作用，但是这是其父类中所拥有的代码，重写完后我复制了过来...结束
        return token;
    }*/
}
