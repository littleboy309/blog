package com.heli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heli.entity.User;
import com.heli.mapper.UserMapper;
import com.heli.service.UserService;
import com.heli.util.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description:
 * date: 2021/1/30 21:24
 * @author heli
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Transactional(readOnly = true)
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
