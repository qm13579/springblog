package people.cn.controller;

import cn.people.domain.Maintenance;
import cn.people.service.IMaintenanceService;
import cn.people.utils.common.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : FENGZHI
 * create at:  2020/2/23  下午10:47
 * @description: 维保接口类
 */
@CrossOrigin
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private IMaintenanceService maintenanceService;

    @ApiOperation(value = "查询所有维保")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllMaintenance(){
        Result result = maintenanceService.findAllMaintenance();
        return result;
    }

    @ApiOperation(value = "新增维保")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addMaintenance(@RequestBody Maintenance maintenance){
        Result result = maintenanceService.addMaintenance(maintenance);
        return result;
    }
    @ApiOperation(value = "根据设备查询维保记录")
    @RequestMapping(value = "/{eid}",method = RequestMethod.GET)
    public Result findMaintenanceByEquipmentId(@PathVariable("eid") String eid){
        Result result = maintenanceService.findMaintenanceByEquipmentId(eid);
        return result;
    }
}
