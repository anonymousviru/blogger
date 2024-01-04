package com.blog.Blogger.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "title should be at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 2,message = "description should be at least 2 characters")
    private String description;
    @NotEmpty
    @Size(min = 2,message = "content should be at least 2 characters")
    private String content;
//    private String message;
}
