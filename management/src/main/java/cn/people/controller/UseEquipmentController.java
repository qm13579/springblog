package cn.people.controller;

import cn.people.domain.UseEquipmentInfo;
import cn.people.service.IUseEquipmentInfoService;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "分配设备到用户，并变更设备使用状态")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addUseEquipment(@RequestBody UseEquipmentInfo info){
        useEquipmentInfoService.addUseEquipment(info);
        return new Result(ResultCode.SUCCESS);

    }

    @ApiOperation(value = "更新使用设备情况")
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

    @ApiOperation(value = "查找当前用户的使用设备情况")
    @RequestMapping(value = "/{uid}",method = RequestMethod.GET)
    public Result findUseEquipmentByUserId(@PathVariable("uid") String uid){
        List<UseEquipmentInfo>  infoList = useEquipmentInfoService.findUseEquipmentByUserId(uid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(infoList);
        return result;
    }
    @ApiOperation(value = "分配设备到用户，并变更设备使用状态及启用设备")
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

    @ApiOperation(value = "查找历史工单")
    @RequestMapping(value = "/history",method = RequestMethod.GET)
    public Result findHistoryUseEquipment(){

        List<UseEquipmentInfo>  useEquipment= useEquipmentInfoService.findHistoryUseEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(useEquipment);
        return result;

    }
    @ApiOperation(value = "查找使用中的工单")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllUseEquipment(){
        List<UseEquipmentInfo> allUseEquipment = useEquipmentInfoService.findAllUseEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(allUseEquipment);
        return result;
    }

    @ApiOperation(value = "通过设备ID获取使用信息")
    @RequestMapping(value = "/equipment/{eid}",method = RequestMethod.GET)
    public Result findUseEquipmentByEId(@PathVariable("eid") String eid){
        System.out.println("equipment/edi");
        Result success = Result.SUCCESS();
        UseEquipmentInfo equipmentInfo = useEquipmentInfoService.findUseEquipmentByEId(eid);
        success.setData(equipmentInfo);
        return success;
    }
}
