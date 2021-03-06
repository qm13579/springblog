package com.blog.controller;

import com.blog.domain.UserInfo;
import com.blog.service.IUserService;
import com.blog.utils.IdWorker;
import com.blog.utils.common.Result;
import com.blog.utils.common.ResultCode;
import com.blog.utils.orcApi.Sample;
import com.sun.net.httpserver.HttpsServer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private Sample sample;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result reach(){
        log.info("进入");
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "search",method = RequestMethod.POST)
    public Result search(@RequestBody Map<String,String> map, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            System.out.println("cookieName: "+cookie.getName()+"cookieValue "+cookie.getValue());
        }
        log.info("访问search");
        String message = map.get("message");
        Result result = new Result(ResultCode.SUCCESS);
        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("message",message);
        result.setData(dateMap);
        return result;
    }

    @RequestMapping(value = "fileLoad",method = RequestMethod.POST)
    public Result fileLoad(@RequestParam MultipartFile file) throws IOException {
        log.info("文件上传");
        String filename = file.getOriginalFilename();
        String substring = filename.substring(filename.lastIndexOf("."));
        String newFileName = idWorker.nextId()+""+substring;
//        String filePath = "D:\\loadfile\\";
        String filePath = "D:\\project\\blog\\src\\main\\resources\\static\\image\\";
        File dest = new File(filePath+newFileName);
        file.transferTo(dest);
        log.info(filePath+newFileName);

//        sample.sample(filePath+newFileName);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "redis测试")
    @RequestMapping(value = "redis/{username}",method = RequestMethod.GET)
    public Result redisTest(@PathVariable("username") String username){
        long begin = System.nanoTime();
        UserInfo user = (UserInfo) redisTemplate.opsForValue().get(username);
        if (user != null){
            // cache in user
            long end = System.nanoTime();
            log.info("this is redis ---->"+user+"time:--->"+(end-begin));
        }
        else {
            //musql findAll
            userService.loadUserByUsername(username);
            user = (UserInfo) redisTemplate.opsForValue().get(username);
            long end = System.nanoTime();
            log.info(" findAll mysql ------>:"+(end-begin));

        }
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(user);
        return result;
    }
}
