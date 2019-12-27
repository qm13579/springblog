package com.blog.controller;

import com.blog.utils.common.Result;
import com.blog.utils.common.ResultCode;
import com.blog.utils.webSocket.WebSocketService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/checkcenter")
public class checkCenterController {

    @RequestMapping(value = "socket/{cid}",method = RequestMethod.POST)
    public Result  socket(@PathVariable String cid){

        return new Result(ResultCode.SUCCESS);

    }
    @RequestMapping(value = "socket/push",method = RequestMethod.PUT)
    public Result pushToWeb(@RequestParam String cid,@RequestParam String message){
        try{
            WebSocketService.sendInfo(message,cid);
        } catch (IOException e){
            e.printStackTrace();
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }

}
