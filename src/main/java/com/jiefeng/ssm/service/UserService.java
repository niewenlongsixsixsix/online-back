package com.jiefeng.ssm.service;

import com.jiefeng.ssm.bean.User;
import com.jiefeng.ssm.dto.UserExecution;

public interface UserService {

    /**
     * 获取所有的用户
     * @return
     */
    UserExecution getAllUser(Integer type);

    User getUserByPrimaryKey(Integer userId);

    boolean updateUserInfo(User user);


}
