package com.blog.service.impl;

import com.blog.domain.Blog;
import com.blog.mapper.BlogMapper;
import com.blog.service.IUserBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBlogServiceImpl implements IUserBlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public List<Blog> findPublishAll(String userId) {

        return blogMapper.findPublishAll(userId);

    }

    @Override
    public void updateUserPublishBlog(Blog blog) {
        blogMapper.updateUserPublishBlog(blog);
    }

    @Override
    public void removedUserBlog(String id) {
        blogMapper.removedUserBlog(id);
    }

    @Override
    public void addUserBlog(Blog blog) {
        blogMapper.addUserBlog(blog);
    }

    @Override
    public List<Blog> findAllBlog() {
        return blogMapper.findAllBlog();
    }
}
