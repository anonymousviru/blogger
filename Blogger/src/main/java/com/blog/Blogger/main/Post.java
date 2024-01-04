package com.blog.Blogger.main;

import java.util.function.Function;
import java.util.function.Supplier;

//public class Post implements Comparable<Post>
public class Post
{
    private String name;
    private double salary;

    public Post(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
//    @Override
//    public int compareTo(Post o) {
//        return this.year-o.year;
//        return this.name.compareTo(o.name);
//    }
}
