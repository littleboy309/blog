package com.heli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heli.entity.Tag;
import javafx.scene.control.TableColumn;

import java.util.List;

/**
 * Description:
 * date: 2021/2/1 11:13
 *
 * @author heli
 * @since JDK 1.8
 */
public interface TagService extends IService<Tag> {

    List<Tag> getAllTag ();

    int saveTag(Tag tag);

    Tag getTagById(Long id);

    List<Tag> getBlogTag();

    Tag getTagByName(String name);

    void deleteTag(Long id);

    void updateTag(Long id, Tag tag);

    List<Tag> getTagByString(String text);
}
