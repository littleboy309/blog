package com.heli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heli.entity.Comment;
import com.heli.mapper.BlogMapper;
import com.heli.mapper.CommentMapper;
import com.heli.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * date: 2021/2/3 12:27
 * @author heli
 * @since JDK 1.8
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private BlogMapper blogMapper;

    /**
     * Description: 查询父评论
     * @author: heli
     */
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        //没有父节点的默认为-1
        List<Comment> comments = commentMapper.findByBlogIdAndParentCommentNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.findByBlogIdAndParentCommentNull(blogId, id);
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * Description: 接收回复的表单
     * @author: heli
     */
    @Transactional(readOnly = false)
    @Override
    public int saveComment(Comment comment) {

        //获得父id
        Long parentCommentId = comment.getParentComment().getId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
            comment.setParentCommentId(parentCommentId);
            comment.setParentComment(commentMapper.findByParentCommentId(comment.getParentCommentId()));
        } else {
            //没有父级评论
            comment.setParentCommentId((long) -1);
            comment.setParentComment(null);

        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }


    /**
     * Description: 判断是否有一级子评论
     * @author: heli
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {

        if (childComments.size() > 0) {
            //循环找出子评论的id
            for (Comment childComment : childComments) {
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    /**
     * Description: 存放迭代找出的所有子代的集合
     * @author: heli
     */
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * Description: 根据子一级评论的id找到子二级评论
     * @author: heli
     */
    private void recursively(Long blogId, Long childId, String parentNickname1) {

        List<Comment> replayComments = commentMapper.findByBlogIdAndParentCommentNull(blogId, childId);

        if (replayComments.size() > 0) {
            for (Comment replayComment : replayComments) {
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId, replayId, parentNickname);
            }
        }
    }
}
