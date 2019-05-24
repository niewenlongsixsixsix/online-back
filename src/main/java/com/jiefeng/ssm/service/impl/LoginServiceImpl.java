package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.LoginExecution;
import com.jiefeng.ssm.enums.LoginStateEnums;
import com.jiefeng.ssm.service.LoginService;
import com.jiefeng.ssm.shiro.realm.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Override
    @Transactional
    public LoginExecution login(Integer type,User user) {

        //先获取登录的主题
        Subject subject = SecurityUtils.getSubject();

        logger.info("SessionID: " +subject.getSession().getId().toString());

        //拿到传过来的用户名
        String username = user.getUsername();
        String password = user.getPassword();

        //封装登录凭证
        UserToken token = new UserToken(username,password,type);

        //如果是普通用户则会选择记住我功能
        token.setRememberMe(true);

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return new LoginExecution(LoginStateEnums.EMPTY);
        }catch (IncorrectCredentialsException e){
            return new LoginExecution(LoginStateEnums.PASSWORD_ERROR);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(subject.isAuthenticated()){

            logger.info(subject.getPrincipal().toString());

            //如果登录成功
            return new LoginExecution(LoginStateEnums.SUCCESS);
        }else{
            //如果没有
            return new LoginExecution(LoginStateEnums.SYSTEM_ERROR);
        }
    }


}
