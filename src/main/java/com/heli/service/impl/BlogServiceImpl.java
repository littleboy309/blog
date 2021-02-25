package com.heli.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heli.entity.Blog;
import com.heli.entity.BlogAndTag;
import com.heli.entity.Tag;
import com.heli.mapper.BlogMapper;
import com.heli.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Description:
 * date: 2021/2/1 12:53
 * @author heli
 * @since JDK 1.8
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Transactional(readOnly = true)
    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Blog getDetailedBlog(Long id) {

        return blogMapper.getDetailedBlog(id);
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<Blog> getAllBlog() {
//        return blogMapper.getAllBlog();
//    }

    @Transactional(readOnly = true)
    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, List<Blog>> archiveBlog() {

        //set去掉重复的年份
        List<String> years = blogMapper.findGroupYear();
        Set<String> set = new HashSet<>(years);
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogMapper.findByYear(year));
        }
        return map;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        // TODO: 2021/2/2 blogandtag
        return blogMapper.searchAllBlog(blog);
    }

    @Transactional(readOnly = false)
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogMapper.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Transactional(readOnly = false)
    @Override
    public int updateBlog(Blog blog) {

        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }

//    @Transactional(readOnly = false)
//    @Override
//    public void deleteBlog(Long id) {
//
//        blogMapper.deleteBlog(id);
//    }

    @Transactional(readOnly = false)
    @Override
    public void updateViews(Long id) {
        blogMapper.updateViews(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Long countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public List<Blog> getAllNewBlog() {
        return blogMapper.getAllNewBlog();
    }


}
