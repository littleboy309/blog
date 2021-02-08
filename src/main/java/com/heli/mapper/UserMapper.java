package com.heli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heli.entity.User;
import com.heli.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * date: 2021/1/30 21:33
 * @author heli
 * @since JDK 1.8
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
