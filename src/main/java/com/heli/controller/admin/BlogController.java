package com.heli.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heli.entity.Blog;
import com.heli.entity.Tag;
import com.heli.entity.Type;
import com.heli.entity.User;
import com.heli.service.BlogService;
import com.heli.service.TagService;
import com.heli.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.List;

/**
 * Description: 后台博客管理
 * date: 2021/1/31 12:27
 *
 * @author heli
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    /**
     * Description: 跳转到blogs-input页面
     *
     * @author: heli
     */
    private static final String INPUT = "admin/blogs-input";

    /**
     * Description: 跳转到blogs页面
     *
     * @author: heli
     */
    private static final String LIST = "admin/blogs";

    /**
     * Description: 重定向到blogs页面
     *
     * @author: heli
     */
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Resource
    private BlogService blogService;

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.list());
        model.addAttribute("tags", tagService.list());
    }

    /**
     * Description: 后台博客列表
     *
     * @author: heli
     */
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false, defaultValue = "1",
            value = "pagenum") int pagenum, Model model) {

        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlog = blogService.list();
        //得到分页结果对象
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);

        setTypeAndTag(model);
        model.addAttribute("pageInfo", pageInfo);
        return LIST;
    }

    /**
     * Description: 按条件查询
     *
     * @author: heli
     */
    @PostMapping("/blogs/search")
    public String searchBlogs(@RequestParam(required = false, defaultValue = "1",
            value = "pagenum") int pagenum, Model model,Blog blog) {

        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlog = blogService.searchAllBlog(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }


    /**
     * Description: 发布博客 去新增页面
     *
     * @author: heli
     */
    @GetMapping("/blogs/input")
    public String toAddBlog(Model model) {

            model.addAttribute("blog", new Blog());
            setTypeAndTag(model);
        return INPUT;
    }

    /**
     * Description: 发布博客 编辑博客页面
     *
     * @author: heli
     */
    @GetMapping("/blogs/{id}/input")
    public String toEditBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        //将tags集合转换为tagIds字符串
        blog.init();
        //返回一个blog对象给前端th:object
        model.addAttribute("blog", blog);
        setTypeAndTag(model);
        return INPUT;
    }

    /**
     * Description: 新增、修改博客内容
     *
     * @author: heli
     */
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes) {

        //设置user属性
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        //id为空，则为新增
        if (blog.getId() == null) {
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("message", "操作成功");
        return REDIRECT_LIST;
    }

    /**
     * Description: 删除
     *
     * @author: heli
     */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.removeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

}
