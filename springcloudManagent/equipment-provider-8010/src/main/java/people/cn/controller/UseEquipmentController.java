package people.cn.controller;


import org.springframework.web.bind.annotation.*;
import people.cn.bean.UseEquipmentInfo;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.server.IUseEquipmentInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/21  下午1:45
 * @description: 设备使用
 */
@CrossOrigin
@RestController
@RequestMapping("/useEquipment")
public class UseEquipmentController {

    @Resource
    private IUseEquipmentInfoService useEquipmentInfoService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addUseEquipment(@RequestBody UseEquipmentInfo info){
        useEquipmentInfoService.addUseEquipment(info);
        return new Result(ResultCode.SUCCESS);

    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result updateUseEquipment(@RequestBody UseEquipmentInfo info){
        System.out.println("this is test");
        boolean b = useEquipmentInfoService.updateUseEquipment(info);
        if (b){
            return Result.SUCCESS();
        }else {
            Result error = Result.ERROR();
            error.setMessage("当前设备状态不允许变更使用人");
            return error;
        }
    }

    @RequestMapping(value = "/{uid}",method = RequestMethod.GET)
    public Result findUseEquipmentByUserId(@PathVariable("uid") String uid){
        List<UseEquipmentInfo>  infoList = useEquipmentInfoService.findUseEquipmentByUserId(uid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(infoList);
        return result;
    }

    @RequestMapping(value = "/metrics",method = RequestMethod.POST)
    public Result metricsUseEquipment(@RequestBody UseEquipmentInfo info){
        boolean b = useEquipmentInfoService.metricsUseEquipment(info);
        if (b){
            return Result.SUCCESS();
        }else {
            Result error = Result.ERROR();
            error.setMessage("当前设备不能变更使用人");
            return error;
        }

    }

    @RequestMapping(value = "/history",method = RequestMethod.GET)
    public Result findHistoryUseEquipment(){

        List<UseEquipmentInfo>  useEquipment= useEquipmentInfoService.findHistoryUseEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(useEquipment);
        return result;

    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllUseEquipment(){
        List<UseEquipmentInfo> allUseEquipment = useEquipmentInfoService.findAllUseEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(allUseEquipment);
        return result;
    }

    @RequestMapping(value = "/equipment/{eid}",method = RequestMethod.GET)
    public Result findUseEquipmentByEId(@PathVariable("eid") String eid){
        System.out.println("equipment/edi");
        Result success = Result.SUCCESS();
        UseEquipmentInfo equipmentInfo = useEquipmentInfoService.findUseEquipmentByEId(eid);
        success.setData(equipmentInfo);
        return success;
    }
}
