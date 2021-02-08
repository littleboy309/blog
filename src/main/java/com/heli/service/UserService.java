package com.heli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heli.entity.User;

/**
 * Description:
 * date: 2021/1/30 21:20
 * @author heli
 * @since JDK 1.8
 */
public interface UserService extends IService<User> {

    /**
    * Description: 检查登录 username,password
    * @author: heli
    * @date: 2021/1/30 21:55
    * @param:
    * @return:
    */
    public User checkUser(String username, String password);
}
