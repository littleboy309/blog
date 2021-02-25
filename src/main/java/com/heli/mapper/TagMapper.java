package com.heli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heli.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 * date: 2021/2/1 11:14
 *
 * @author heli
 * @since JDK 1.8
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

//    List<Tag> getAllTag ();

    int saveTag(Tag tag);

    Tag getTagById(Long id);

    List<Tag> getBlogTag();

    Tag getTagByName(String name);

//    void deleteTag(Long id);

    void updateTag(Tag tag);


}
