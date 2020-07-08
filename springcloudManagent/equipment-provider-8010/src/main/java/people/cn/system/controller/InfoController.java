package people.cn.system.controller;


import com.github.pagehelper.PageInfo;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import people.cn.bean.EquipmentInfo;
import people.cn.bean.InfoPojo;
import people.cn.bean.LogDot;
import people.cn.common.IdWorker;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.system.server.IInfoService;

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

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addInfo(@RequestBody InfoPojo info){

      return new Result(ResultCode.SUCCESS);
    };

    @RequestMapping(value = "/{status}",method = RequestMethod.GET)
    public Result findAllInfo(@PathVariable("status") String status){

        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/{uid}/{status}",method = RequestMethod.GET)
    public Result findAllByUserId(@PathVariable("uid") String uid,@PathVariable("status") String status){
        Result result = new Result(ResultCode.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/pushToUser",method = RequestMethod.POST)
    public Result InfoPushToUsers(@RequestBody InfoPojo info){

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/pushToUserGroup",method = RequestMethod.POST)
    public Result InfoPushUserGroup(@RequestBody InfoPojo info){
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/pushToRole",method = RequestMethod.POST)
    public  Result InfoPushToRoles(@RequestBody InfoPojo info){

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/count/{uid}",method = RequestMethod.GET)
    public Result countInfoByuser(@PathVariable("uid")String uid){
        Result result = new Result(ResultCode.SUCCESS);

        return result;
    }

    @RequestMapping(value = "/log",method = RequestMethod.POST)
    public Result findLogInfo(@RequestBody LogDot logDot){
        Result result = new Result(ResultCode.SUCCESS);
        System.out.println(logDot.toString());
        PageInfo logs= infoService.findLog(logDot);
        result.setData(logs);
        return result;
    }

    @RequestMapping(value = "/pdf",method = RequestMethod.GET)
    public void getInfoPDF(HttpServletResponse response) throws IOException, DocumentException {
        infoService.getInfoPdf(response);
    }

    @RequestMapping(value = "/equipment/{equipmentId}",method = RequestMethod.GET)
    public Result getEquipmentInfo(@PathVariable("equipmentId") String equipmentId){
        List<EquipmentInfo> equipmentInfoList = infoService.getEquipmentInfo(equipmentId);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(equipmentInfoList);
        return result;
    }

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
