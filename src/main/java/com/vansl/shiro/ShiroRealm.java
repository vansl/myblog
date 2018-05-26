package com.vansl.shiro;

/**
 * @author: vansl
 * @create: 18-5-8 下午5:02
 */
import com.vansl.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //角色和权限控制
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(userService.selectRoleByUserName(username));
        return authorizationInfo;
    }

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
        //获取用户输入的用户名
        String userName = (String)authcToken.getPrincipal();

        //通过数据库查询密码
        String password = userService.selectPasswordByUserName(userName);
        //帐号错误抛出异常
        if(password==null){
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(userName,password, getName());
    }

}
