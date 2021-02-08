package com.heli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heli.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * date: 2021/2/3 12:15
 *
 * @author heli
 * @since JDK 1.8
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
    * Description: 根据创建时间倒序来排
    * @author: heli
    */
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    /**
     * Description: 查询父级对象
     * @author: heli
     */
    Comment findByParentCommentId(@Param("parentCommentId")Long parentcommentid);

    /**
     * Description: 添加一个评论
     * @author: heli
     */
    int saveComment(Comment comment);


}
