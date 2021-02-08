package com.heli.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heli.NotFoundException;
import com.heli.entity.Blog;
import com.heli.service.BlogService;
import com.heli.service.TagService;
import com.heli.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 前台首页
 * date: 2021/1/30 10:50
 * @author heli
 * @since JDK 1.8
 */
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;


    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "1",
            value = "pagenum") int pagenum, Model model) {

        PageHelper.startPage(pagenum, 4);
        List<Blog> allBlog = blogService.getIndexBlog();
        //得到分页结果对象
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("types", typeService.getBlogType());
        model.addAttribute("tags", tagService.getBlogTag());
        //获取推荐博客
        List<Blog> recommendBlog = blogService.getAllRecommendBlog();
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    /**
     * Description: 全局查询
     *
     * @author: heli
     */
    @PostMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1",
            value = "pagenum") int pagenum, @RequestParam String query, Model model) {
        PageHelper.startPage(pagenum, 6);
        List<Blog> allBlog = blogService.getSearchBlog(query);
        //得到分页结果对象
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        return "search";
    }

    /**
    * Description: 博客详情
    * @author: heli
    */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {

        model.addAttribute("blog",blogService.getDetailedBlog(id));
        blogService.updateViews(id);
        return "blog";
    }

    /**
    * Description: footer的最新博客
    * @author: heli
    */
    @GetMapping("/footer/newblog")
    public String newblogs(Model model){

        model.addAttribute("newblogs",blogService.getAllNewBlog());
        return "_fragments :: newblogList";
    }
}
