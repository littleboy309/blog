package com.heli.controller;

import com.heli.entity.Comment;
import com.heli.entity.User;
import com.heli.service.BlogService;
import com.heli.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * date: 2021/2/3 12:04
 *
 * @author heli
 * @since JDK 1.8
 */
@Controller
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    /**
    * Description: 把评论头像定死
    * @author: heli
    */
    @Value("${comment.avatar}")
    private String avatar;

    /**
    * Description: 获取对应博客的评论信息
    * @author: heli
    */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.getCommentByBlogId(blogId));
        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

    /**
    * Description: 提交评论
    * @author: heli
    */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        //绑定博客与评论
        comment.setBlog(blogService.getDetailedBlog(blogId));
        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("user");
        //用户为管理员
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
