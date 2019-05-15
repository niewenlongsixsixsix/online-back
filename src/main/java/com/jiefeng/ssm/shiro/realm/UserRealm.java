package com.jiefeng.ssm.shiro.realm;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.beanExtend.UserExtend;
import com.jiefeng.ssm.dao.PermissionDao;
import com.jiefeng.ssm.dao.RoleDao;
import com.jiefeng.ssm.dao.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserDao userDao;

    //用于返回当前Realm的名字
    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserExtend principal = (UserExtend) principalCollection.getPrimaryPrincipal();

        //获取用户ID
        long userId = principal.getId();

        //将两个List封装，将其返回出去，方便进行对比
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        simpleAuthorizationInfo.addRoles(roleDao.getUserAllRole(userId));
        simpleAuthorizationInfo.addStringPermissions(permissionDao.getUserAllPermission(userId));

        return simpleAuthorizationInfo;
    }

    //
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        //看数据库中是否有用户名，如果有的话将用户数据取出来
        User userInfo = userDao.getPasswordByEmail(principal);

        if(userInfo == null){
            throw new UnknownAccountException();//没有此用户
        }

        UserExtend userExtend = new UserExtend();

        //将用户的ID返回出去，方便以后进行操作
        userExtend.setId(userInfo.getId());
        userExtend.setUsername(userInfo.getUsername());

        //将查询出来的数据返回出去方便认证
        //第一个参数如果验证成功的话，将会被设置到subject.getPrincipal()中
        //参数（用户名，数据库拿回来的密码，当前Realm的名字)
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userExtend,userInfo.getPassword(), ByteSource.Util.bytes(userInfo.getSalt()),getName());

        return info;
    }
}