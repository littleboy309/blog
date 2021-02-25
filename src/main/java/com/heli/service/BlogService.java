package com.heli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heli.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/2/1 12:40
 * @author heli
 * @since JDK 1.8
 */
public interface BlogService extends IService<Blog> {

    Blog getBlogById(Long id);

    Blog getDetailedBlog(Long id);

//    List<Blog> getAllBlog();

    List<Blog> getByTypeId(Long typeId);

    List<Blog> getByTagId(Long tagId);

    List<Blog> getIndexBlog();

    List<Blog> getAllRecommendBlog();

    List<Blog> getSearchBlog(String query);

    Map<String,List<Blog>> archiveBlog();

    List<Blog> searchAllBlog(Blog blog);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

//    void deleteBlog(Long id);

    void updateViews(Long id);

    Long countBlog();

    List<Blog> getAllNewBlog();

}
