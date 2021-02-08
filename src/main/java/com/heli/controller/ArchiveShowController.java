package com.heli.controller;

import com.heli.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * Description:
 * date: 2021/2/4 15:01
 * @author heli
 * @since JDK 1.8
 */
@Controller
public class ArchiveShowController {

    @Resource
    private BlogService blogService;

    @GetMapping("archives")
    public String archives(Model model){

        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }

}
