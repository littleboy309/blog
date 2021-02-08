package com.heli.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heli.entity.Blog;
import com.heli.entity.Type;
import com.heli.service.BlogService;
import com.heli.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * date: 2021/2/4 10:16
 * @author heli
 * @since JDK 1.8
 */
@Controller
public class TypeShowController {

    @Resource
    private TypeService typeService;

    @Resource
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(required = false, defaultValue = "1",
            value = "pagenum") int pagenum, Model model, @PathVariable Long id){

        //开启分页
        PageHelper.startPage(pagenum, 100);
        List<Type> types = typeService.getBlogType();
        //-1从导航点过来的
        if (id == -1){
            id = types.get(0).getId();
        }
        List<Blog> blogs = blogService.getByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        //当前活跃的type
        model.addAttribute("activeTypeId", id);

        return "types";
    }
}
