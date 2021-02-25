package com.heli.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heli.entity.Type;
import com.heli.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * date: 2021/1/31 14:06
 *
 * @author heli
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
    * Description: 分类页面
    * @author: heli
    */
    @GetMapping("/types")
    public String types(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Type> allType = typeService.list();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";

    }

    /**
    * Description: 新增分类 跳转页面
    * @author: heli
    */
    @GetMapping("/types/input")
    public String input(Model model){

        //初始化
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * Description: 提交分类编辑
     * @author: heli
     */
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){

        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 == null && StringUtils.trimAllWhitespace(type.getName()).length() != 0){
            attributes.addFlashAttribute("message","新增成功");
            typeService.saveType(type);
            return "redirect:/admin/types";
        } else {
            attributes.addFlashAttribute("message","新增失败:存在相同分类");
            return "redirect:/admin/types/input";
        }
    }

    /**
     * Description: 修改分类 跳转页面
     * @author: heli
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){

        model.addAttribute("type",typeService.getTypeById(id));
        return "admin/types-input";
    }

    /**
     * Description: 修改分类提交
     * @author: heli
     */
    @PostMapping("/types/{id}")
    public String editPost(Type type,@PathVariable Long id, RedirectAttributes attributes){

        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 == null && StringUtils.trimAllWhitespace(type.getName()).length() != 0){
            attributes.addFlashAttribute("message","更新成功");
            typeService.updateType(id,type);
            return "redirect:/admin/types";
        } else {
            attributes.addFlashAttribute("message","更新失败");
            return "redirect:/admin/types/input";
        }
    }

    /**
     * Description: 删除分类
     * @author: heli
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){

        typeService.removeById(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
