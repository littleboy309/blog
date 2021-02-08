package com.heli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * date: 2021/1/30 20:00
 *
 * @author heli
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Long id;
    /**
    * Description: 昵称
    * @author: heli
    */
    private String nickname;
    private String username;
    private String password;
    private String email;
    /**
    * Description: 头像
    * @author: heli
    */
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs = new ArrayList<>();

}
