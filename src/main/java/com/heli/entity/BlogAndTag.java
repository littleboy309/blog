package com.heli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Description: 把博客和标签关系存到数据库中使用的类
 * date: 2021/2/1 12:47
 * @author heli
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogAndTag {

    private Long tagId;

    private Long blogId;
}
