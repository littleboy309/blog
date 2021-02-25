package com.heli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heli.entity.Tag;
import com.heli.mapper.TagMapper;
import com.heli.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/2/1 11:14
 *
 * @author heli
 * @since JDK 1.8
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

//    @Transactional(readOnly = true)
//    @Override
//    public List<Tag> getAllTag() {
//        return tagMapper.getAllTag();
//    }

    @Transactional(readOnly = false)
    @Override
    public int saveTag(Tag tag) {

        // TODO: 2021/2/2 tagandblog
        return tagMapper.saveTag(tag);
    }

    @Transactional(readOnly = true)
    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tag> getBlogTag() {
        return tagMapper.getBlogTag();
    }


    @Transactional(readOnly = true)
    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

//    @Transactional(readOnly = false)
//    @Override
//    public void deleteTag(Long id) {
//        // TODO: 2021/2/2 tagandblog
//         tagMapper.deleteTag(id);
//    }

    @Transactional(readOnly = false)
    @Override
    public void updateTag(Long id, Tag tag) {
        // TODO: 2021/2/2 tagandblog
         tagMapper.updateTag(tag);
    }

    /**
    * Description: 从tagIds字符串中获取id，根据id获取tag集合
    * @author: heli
    */
    @Override
    @Transactional(readOnly = true)
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long long1 : longs) {
            tags.add(tagMapper.getTagById(long1));
        }
        return tags;
    }

    /** 把前端的tagIds字符串转换为list集合
    * Description:
    * @author: heli
    */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
