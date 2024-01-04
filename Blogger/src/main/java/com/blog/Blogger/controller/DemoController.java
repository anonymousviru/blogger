package com.blog.Blogger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @GetMapping("/hello")
    public String hello() {
        return "hello"; // Returns "hello.jsp"
    }
    @RequestMapping("/d")
    public String helloo() {
        return "hello"; // Returns "hello.jsp"
    }
}
