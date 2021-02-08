package com.heli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 * date: 2021/2/4 16:01
 * @author heli
 * @since JDK 1.8
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){

        return "about";
    }
}
