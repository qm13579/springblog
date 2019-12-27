package com.blog.controller;

import com.blog.utils.common.Result;
import com.blog.utils.common.ResultCode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/hello")
@RestController
public class HelloController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result hello(Principal principal){
        System.out.println(principal);
        return new Result(ResultCode.SUCCESS);
    }

}
