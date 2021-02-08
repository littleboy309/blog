package com.heli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heli.entity.Type;

import java.util.List;

/**
 * Description:
 * date: 2021/1/31 13:35
 *
 * @author heli
 * @since JDK 1.8
 */

public interface TypeService extends IService<Type> {

    int saveType(Type type);

    Type getTypeById(Long id);

    List<Type> getAllType();

    Type getTypeByName(String name);


    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    void updateType(Long id,Type type);


    void deleteTypeById(Long id);
}
