package people.cn.system.controller;


import org.springframework.web.bind.annotation.*;
import people.cn.bean.Maintenance;
import people.cn.common.Result;
import people.cn.system.server.IMaintenanceService;

/**
 * @author : FENGZHI
 * create at:  2020/2/23  下午10:47
 * @description: 维保接口类
 */
@CrossOrigin
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

//    @Autowired
    private IMaintenanceService maintenanceService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllMaintenance(){
        Result result = maintenanceService.findAllMaintenance();
        return result;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result addMaintenance(@RequestBody Maintenance maintenance){
        Result result = maintenanceService.addMaintenance(maintenance);
        return result;
    }
    @RequestMapping(value = "/{eid}",method = RequestMethod.GET)
    public Result findMaintenanceByEquipmentId(@PathVariable("eid") String eid){
        Result result = maintenanceService.findMaintenanceByEquipmentId(eid);
        return result;
    }
}
