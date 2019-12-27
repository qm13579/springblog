package com.blog.controller;

import com.blog.domain.Blog;
import com.blog.service.IUserBlogService;
import com.blog.utils.common.Result;
import com.blog.utils.common.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/userblog")
@RestController
@CrossOrigin
public class UserBlogController {
    @Autowired
    private IUserBlogService userBlogService;

    @ApiOperation(value = "查找当前用户已发博客信息")
    @RequestMapping(value = "findAll/{userId}",method = RequestMethod.GET)
    public Result findAllUserPublishBlog(@PathVariable String userId){
        List<Blog> publishAll= userBlogService.findPublishAll(userId);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(publishAll);
        return result;
    }

    @ApiOperation(value = "更新当前已发布的博文")
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Result updateUserPublicBlog(@RequestBody Blog blog){
        userBlogService.updateUserPublishBlog(blog);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "当前用户的博文下架")
    @RequestMapping(value = "removed/{id}",method = RequestMethod.DELETE)
    public Result removedUserBlog(@PathVariable String id){
        userBlogService.removedUserBlog(id);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "当前用户新增博文")
    @RequestMapping(value = "addblog",method = RequestMethod.POST)
    public Result addUserBlog(@RequestBody Blog blog){
        userBlogService.addUserBlog(blog);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "博客显示")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Result findAllBlog(){
        List<Blog> blogList = userBlogService.findAllBlog();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(blogList);
        return result;

    }
}
