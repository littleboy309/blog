package com.heli.mapper;

import com.heli.entity.Blog;
import com.heli.entity.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * date: 2021/2/1 12:42
 * @author heli
 * @since JDK 1.8
 */
@Mapper
public interface BlogMapper {

    /**
    * Description:
    * @author: heli
     * @param id
     * @return
    */
    Blog getBlogById(Long id);

    /**
     * Description:博客详情
     * @author: heli
     * @param id
     * @return
     */
    Blog getDetailedBlog(@Param("id") Long id);

    /**
     * Description:博客
     * @author: heli
     * @param
     * @return
     */
    List<Blog> getAllBlog();

    /**
     * Description: 根据类型id获取博客
     * @author: heli
     * @param typeId
     * @return
     */
    List<Blog> getByTypeId(Long typeId);

    /**
     * Description: 根据标签id获取博客
     * @author: heli
     * @param tagId
     * @return
     */
    List<Blog> getByTagId(Long tagId);

    /**
     * Description: 主页博客展示
     * @author: heli
     * @param
     * @return
     */
    List<Blog> getIndexBlog();

    /**
     * Description: 推荐博客展示
     * @author: heli
     * @param
     * @return
     */
    List<Blog> getAllRecommendBlog();

    /**
     * Description: 全局搜索博客
     * @author: heli
     * @param query
     * @return
     */
    List<Blog> getSearchBlog(String query);

    /**
     * Description: 后台根据标题、分类、推荐搜索博客
     * @author: heli
     * @param blog
     * @return
     */
    List<Blog> searchAllBlog(Blog blog);

    /**
     * Description: 查询所有年份，返回一个集合
     * @author: heli
     * @param
     * @return
     */
    List<String> findGroupYear();

    /**
     * Description: 按年份查询博客
     * @author: heli
     * @param year
     * @return
     */
    List<Blog> findByYear(@Param("year") String year);

    /**
     * Description: 保存博客
     * @author: heli
     * @param blog
     * @return
     */
    int saveBlog(Blog blog);

    /**
     * Description:
     * @author: heli
     * @param blogAndTag
     * @return
     */
    void saveBlogAndTag(BlogAndTag blogAndTag);

    /**
     * Description: 更新博客
     * @author: heli
     * @param blog
     * @return
     */
    int updateBlog(Blog blog);

    /**
     * Description: 删除博客
     * @author: heli
     * @param id
     * @return
     */
    void deleteBlog(Long id);

    /**
     * Description: 更新浏览次数
     * @author: heli
     * @param id
     * @return
     */
    void updateViews(Long id);

    /**
    * Description: 查询总博客
    * @author: heli
    */
    Long countBlog();

    /**
     * Description: 查询最新博客
     * @author: heli
     */
    List<Blog> getAllNewBlog();

}
