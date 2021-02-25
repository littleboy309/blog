package com.heli.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 分类
 * date: 2021/1/30 19:52
 * @author heli
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Type {

    private Long id;
    private String name;

    @TableLogic
    private Integer deleted;

    /**
     * 一个分类有多个博客
     */
    @TableField(exist = false)
    private List<Blog> blogs = new ArrayList<>();
}
