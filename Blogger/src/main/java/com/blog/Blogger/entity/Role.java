package com.blog.Blogger.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users=new HashSet<>();
    //duplicate element are not allowed in set but in case oflist duplicate elementds
    // are a;;pwed users canot be duplicate
    //while developng sping security feature i have implimented data structure that is set
    // because i donot want to duplicate value
//for list suppose there is a post and coomments if there is multiple comment,
// we need to impliment list

    // constructors, getters, and setters

    // additional methods if needed
}
