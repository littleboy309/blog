package com.heli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heli.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 * date: 2021/1/31 13:44
 *
 * @author heli
 * @since JDK 1.8
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {

    int saveType(Type type);

    Type getTypeById(Long id);

//    List<Type> getAllType();

    Type getTypeByName(String name);

    List<Type> getBlogType();  //首页右侧展示type对应的博客数量

    void updateType(Type type);

//    void deleteTypeById(Long id);
}
