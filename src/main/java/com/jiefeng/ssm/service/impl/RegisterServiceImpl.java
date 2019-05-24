package com.jiefeng.ssm.service.impl;

import com.jiefeng.ssm.bean.Admin;
import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dao.AdminDao;
import com.jiefeng.ssm.dao.RoleDao;
import com.jiefeng.ssm.dao.UserDao;
import com.jiefeng.ssm.dto.RegisterExecution;
import com.jiefeng.ssm.enums.RegisterStateEnums;
import com.jiefeng.ssm.exception.RegisterOperationException;
import com.jiefeng.ssm.redis.JedisUtil;
import com.jiefeng.ssm.service.RegisterService;
import com.jiefeng.ssm.util.MailUtil;
import com.jiefeng.ssm.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("RegisterService")
public class RegisterServiceImpl implements RegisterService {

    private static final String REDIS_VERIFY_CODE = ":verifyCode";

    @Autowired
    private JedisUtil.Strings strings;

    @Autowired
    private JedisUtil.Keys keys;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;


    @Override
    @Transactional
    public RegisterExecution addUser(User user) {

        boolean b;

        try{
            //添加用户
            b = userDao.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
            //产生异常，事务回滚
            throw new RegisterOperationException("系统异常");
        }

        if(!b){
            return new RegisterExecution(RegisterStateEnums.SYSTEM_ERROR);
        }

        return new RegisterExecution(RegisterStateEnums.SUCCESS);
    }

    @Override
    @Transactional
    public RegisterExecution addAdmin(Admin admin) {
        boolean b;
        try{
            b = adminDao.addAdmin(admin);
        }catch (Exception e){
            e.printStackTrace();
            //产生异常，事务回滚
            throw new RegisterOperationException("addAdmin 系统异常");
        }

        if(!b){
            return new RegisterExecution(RegisterStateEnums.SYSTEM_ERROR);
        }
        return new RegisterExecution(RegisterStateEnums.SUCCESS);
    }

    /**
     * 生成验证码
     * 发送验证码到对应邮箱上面
     * 最后放到Redis中，key为 email:verifyCode 并且设置过期时间为5分钟
     * @return
     */
    @Transactional
    @Override
    public RegisterExecution generateVerifyCode(String email,String username) {

        //生成此邮箱在Redis中对应的key
        String key = getVerifyCodeKey(email);

        int verifyCode = VerifyCodeUtil.getVerifyCode();

        try{
            MailUtil.sendMail(email,username,verifyCode);
        }catch (Exception e){
            throw new RegisterOperationException("验证码发送失败");
        }

        strings.setEx(key,300,verifyCode +"");

        return new RegisterExecution(RegisterStateEnums.SUCCESS);
    }


    /**
     * 验证验证码是否正确
     * @param email
     * @param verifyCodeToken
     * @return
     */
    @Override
    public RegisterExecution validationVerifyCode(String email,String verifyCodeToken) {


        String verifyCodeKey = getVerifyCodeKey(email);

        try{
            //判断传进来的验证码是不是为空
            if(verifyCodeToken.isEmpty()){
                return new RegisterExecution(RegisterStateEnums.EMPTY);
            }

            //判断验证码是否已经失效
            if(!keys.exists(verifyCodeKey)){
                return new RegisterExecution(RegisterStateEnums.VERIFY_CODE_INVALID);
            }

            //判断验证码是否一样
            if(!verifyCodeToken.equals(strings.get(verifyCodeKey))){
                return new RegisterExecution(RegisterStateEnums.VERIFY_CODE_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new RegisterExecution(RegisterStateEnums.SUCCESS);
    }


    /**
     * 获取对应邮箱的验证码key
     * @param email
     * @return
     */
    public static String getVerifyCodeKey(String email){

        String key = email + RegisterServiceImpl.REDIS_VERIFY_CODE;

        return key;
    }


}
