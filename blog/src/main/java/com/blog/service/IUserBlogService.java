package com.blog.service;

import com.blog.domain.Blog;

import java.util.List;

public interface IUserBlogService {

    List<Blog> findPublishAll(String userId);

    void updateUserPublishBlog(Blog blog);

    void removedUserBlog(String id);

    void addUserBlog(Blog blog);

    List<Blog> findAllBlog();
}
