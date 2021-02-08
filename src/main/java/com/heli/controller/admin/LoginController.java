package com.heli.controller.admin;

import com.heli.entity.User;
import com.heli.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Description: 后台登录页面
 * date: 2021/1/30 22:05
 * @author heli
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * Description: 直接进入后台登录页面
     * @author: heli
     */
    @GetMapping
    public String loginPage() {

        return "admin/login";
    }

    /**
     * Description: 提交表单登录
     * @author: heli
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) {

        User user = userService.checkUser(username, password);
        if (user != null) {
            //把password设为null，防止将密码穿到session，造成泄漏
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");

            return "redirect:/admin";
        }
    }

    /**
     * Description: 注销
     * @author: heli
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        //清除session域中的信息
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
