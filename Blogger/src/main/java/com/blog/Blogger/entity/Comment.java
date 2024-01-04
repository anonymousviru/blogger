package com.blog.Blogger.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;
@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
@Table(name="Comments")
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String body;
    @ManyToOne
    @JoinColumn(name="post_id")//foreign key
    @ToString.Exclude
    private  Post post;
}
