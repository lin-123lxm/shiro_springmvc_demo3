package com.huikedu.realm;

import com.huikedu.domain.AdminUser;
import com.huikedu.services.AdminUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserService adminUserService;
    /*
    授权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username =(String) principals.getPrimaryPrincipal();
        // 1、对当前登录的账号进行角色的授权。
        simpleAuthorizationInfo.addRoles(adminUserService.findRolesByUserName(username));
        // 2、对当前登录的账号进行权限的授权。
        simpleAuthorizationInfo.addStringPermissions(adminUserService.findPermissionsByUserName(username));
        return simpleAuthorizationInfo;
    }


    /*
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        AdminUser adminUser = adminUserService.QueryUserByName(username);
        if (adminUser == null) {
           return  null;
        }
        // 账号被禁
        if (adminUser.getIsDisabled()) {
            throw new AuthenticationException("账号已被禁用");
        }
        SimpleAuthenticationInfo
                simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(
                        adminUser.getAccount(),
                        adminUser.getPassword(),
                        ByteSource.Util.bytes(adminUser.getAccount()),
                        getName());
        return simpleAuthenticationInfo;
    }
}
