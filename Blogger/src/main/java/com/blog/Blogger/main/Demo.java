package com.blog.Blogger.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Demo {
    public static void main(String[] args) {
        BCryptPasswordEncoder c = new BCryptPasswordEncoder();
        System.out.println(c.encode("test"));
        // new Demo().test().test2();
    }
//    public Demo test(){return new Demo();}
//    public void test2(){
//        System.out.println(100);
//    }
}
