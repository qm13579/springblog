package com.blog.controller;

import com.blog.domain.UserInfo;
import com.blog.service.impl.UserServiceImpl;
import com.blog.utils.common.Result;
import com.blog.utils.common.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value="register",method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result register(@RequestBody UserInfo user){
        redisTemplate.opsForValue().set("fengzhi","reg");
        userService.save(user);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "login/",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result login(@RequestParam String username,@RequestParam String password){
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "changePassword",method = RequestMethod.PUT)
    public Result changePassword(@RequestParam String password,@RequestParam String newPassword1 ,@RequestParam String newPassword2){
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "userInfo",method = RequestMethod.PUT)
    @ApiOperation(value = "信息修改")
    public Result UserInfo(@RequestBody UserInfo user){
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "fileLoad",method = RequestMethod.POST)
    public Result fileLoad(@RequestParam MultipartFile file) throws IOException {
        try{
            userService.loadFile(file);
            return new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            System.out.println(e);
            return new Result(ResultCode.FAIL);
        }
    }
}
