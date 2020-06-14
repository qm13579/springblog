package cn.people.controller;

import cn.people.domain.EquipmentInfo;
import cn.people.domain.InfoPojo;
import cn.people.domain.LogPojo;
import cn.people.dot.LogDot;
import cn.people.service.IInfoService;
import cn.people.utils.IdWorker;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实现信息新增和推送
 * @author : FENGZHI
 * create at:  2020/2/24  下午11:32
 * @description: 信息推送控制层
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private IInfoService infoService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "新增消息不推送")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addInfo(@RequestBody InfoPojo info){

      return new Result(ResultCode.SUCCESS);
    };
    @ApiOperation(value = "根据信息发布状态查询信息")
    @RequestMapping(value = "/{status}",method = RequestMethod.GET)
    public Result findAllInfo(@PathVariable("status") String status){

        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }
    @ApiOperation(value = "查询用户已读信息或未读信息")
    @RequestMapping(value = "/{uid}/{status}",method = RequestMethod.GET)
    public Result findAllByUserId(@PathVariable("uid") String uid,@PathVariable("status") String status){
        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }
    @ApiOperation(value = "将信息推送到指定用户")
    @RequestMapping(value = "/pushToUser",method = RequestMethod.POST)
    public Result InfoPushToUsers(@RequestBody InfoPojo info){

        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "将信息推送到指定用户组")
    @RequestMapping(value = "/pushToUserGroup",method = RequestMethod.POST)
    public Result InfoPushUserGroup(@RequestBody InfoPojo info){
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "将信息推送到指定角色下的所有用户")
    @RequestMapping(value = "/pushToRole",method = RequestMethod.POST)
    public  Result InfoPushToRoles(@RequestBody InfoPojo info){

        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "查询当前用户有多少未读消息")
    @RequestMapping(value = "/count/{uid}",method = RequestMethod.GET)
    public Result countInfoByuser(@PathVariable("uid")String uid){
        Result result = new Result(ResultCode.SUCCESS);

        return result;
    }
    @ApiOperation(value = "查看日志")
    @RequestMapping(value = "/log",method = RequestMethod.POST)
    public Result findLogInfo(@RequestBody LogDot logDot){
        Result result = new Result(ResultCode.SUCCESS);
        System.out.println(logDot.toString());
        PageInfo logs= infoService.findLog(logDot);
        result.setData(logs);
        return result;
    }
    @ApiOperation(value = "获取信息文档")
    @RequestMapping(value = "/pdf",method = RequestMethod.GET)
    public void getInfoPDF(HttpServletResponse response) throws IOException, DocumentException {
        infoService.getInfoPdf(response);
    }
    @ApiOperation(value = "获取设备信息")
    @RequestMapping(value = "/equipment/{equipmentId}",method = RequestMethod.GET)
    public Result getEquipmentInfo(@PathVariable("equipmentId") String equipmentId){
        List<EquipmentInfo> equipmentInfoList = infoService.getEquipmentInfo(equipmentId);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(equipmentInfoList);
        return result;
    }

    @ApiOperation(value = "获取一周的访问数据")
    @RequestMapping(value = "/pv",method = RequestMethod.GET)
    public Result getWeekPVDate(){
        Result result = new Result(ResultCode.SUCCESS);
        try {
            List<String> list = (List<String>) redisTemplate.opsForValue().get("weekPV");
            if (list == null){
                list= infoService.getWeekPVDate();
                redisTemplate.opsForValue().set("weekPV",list);
                redisTemplate.expire("weekPV",60*10,TimeUnit.SECONDS);
                log.info("把pv数据放置在缓存中");
            }
            int pvNumToday = (int) redisTemplate.opsForValue().get("pvNum");

            HashMap<Object, Object> map = new HashMap<>(3);
            map.put("first",list.get(list.size()-1) + pvNumToday==null?0:pvNumToday);
            map.put("last",list.get(0));
            map.put("week",list);
            result.setData(map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }

    }
}
