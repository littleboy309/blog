package com.heli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * date: 2021/1/30 19:55
 *
 * @author heli
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;

    /**
     * 是否为管理员评论
     */
    private boolean adminComment;
    /**
     * 头像
     */
    private String avatar;
    private Date createTime;

    private Long blogId;
    /**
     * 父评论id
     */
    private Long parentCommentId;
    private String parentNickname;

    /**
     * 回复评论
     */
    private List<Comment> replyComments = new ArrayList<>();

    /**
     * 父评论
     */
    private Comment parentComment;

    private Blog blog;
}
