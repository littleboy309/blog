package com.heli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heli.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * date: 2021/2/3 12:27
 *
 * @author heli
 * @since JDK 1.8
 */
public interface CommentService extends IService<Comment> {

    List<Comment> getCommentByBlogId(Long id);

    int saveComment(Comment comment);

}
