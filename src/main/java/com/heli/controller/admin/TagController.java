package com.heli.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heli.entity.Tag;
import com.heli.entity.Type;
import com.heli.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * date: 2021/2/1 11:12
 * @author heli
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * Description: 标签页面
     * @author: heli
     */
    @GetMapping("/tags")
    public String tags(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model){

        PageHelper.startPage(pagenum, 5);
        List<Tag> allTag = tagService.getAllTag();
        //得到分页结果对象
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    /**
     * Description: 新增标签 跳转页面
     * @author: heli
     */
    @GetMapping("/tags/input")
    public String input(){

        return "admin/tags-input";
    }

    /**
     * Description: 提交标签编辑
     * @author: heli
     */
    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes){

        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 == null && StringUtils.trimAllWhitespace(tag.getName()).length() != 0){
            attributes.addFlashAttribute("message","新增成功");
            tagService.saveTag(tag);
            return "redirect:/admin/tags";
        } else {
            attributes.addFlashAttribute("message","新增失败:存在相同标签");
            return "redirect:/admin/tags/input";
        }
    }

    /**
     * Description: 修改标签 跳转页面
     * @author: heli
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){

        model.addAttribute("tag",tagService.getTagById(id));
        return "admin/tags-input";
    }

    /**
     * Description: 修改标签提交
     * @author: heli
     */
    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, @PathVariable Long id, RedirectAttributes attributes){

        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 == null && StringUtils.trimAllWhitespace(tag.getName()).length() != 0){
            attributes.addFlashAttribute("message","更新成功");
            tagService.updateTag(id,tag);
            return "redirect:/admin/tags";
        } else {
            attributes.addFlashAttribute("message","更新失败");
            return "redirect:/admin/tags/input";
        }
    }

    /**
     * Description: 删除标签
     * @author: heli
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){

        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
