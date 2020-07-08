package people.cn.system.controller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import people.cn.bean.Equipment;
import people.cn.bean.GroupDict;
import people.cn.bean.UseEquipmentInfo;
import people.cn.common.ExcelUtil;
import people.cn.common.ExcelUtils;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.common.factory.FileFactory;
import people.cn.system.server.IEquipmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/14  下午8:58
 * @description: 设备
 */
@CrossOrigin
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;
    @Autowired
    private ExcelUtil excelUtil;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllEquipment(){
        List<Equipment> equipmentList = equipmentService.findAllEquipment();
        System.out.println(equipmentList);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(equipmentList);
        return result;
    }

    @RequestMapping(value = "metrics",method = RequestMethod.GET)
    public Result findAllEquipmentMetrics(){
        List<Equipment> equipments = equipmentService.findAllEquipmentMetricsIsNot();
        Result success = Result.SUCCESS();
        success.setData(equipments);
        return success;
    }

    @RequestMapping(value = "/{gid}",method = RequestMethod.GET)
    public Result findEquipmentByGroupId(@PathVariable("gid") String gid){
        System.out.println(gid);
        List<Equipment> equipment = equipmentService.findEquipmentByGroupId(gid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(equipment);
        return result;
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Result updateEquipment(@RequestBody Equipment equipment){
        boolean result = equipmentService.updateEquipment(equipment);
        if (!result){
            Result error = Result.ERROR();
            error.setMessage("设备未启用，不能分配用户");
            return error;
        }
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result saveEquipment(@RequestBody Equipment equipment){
        equipmentService.saveEquipment(equipment);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/use",method = RequestMethod.POST)
    public Result saveUseEquipment(@RequestBody UseEquipmentInfo info){

        equipmentService.saveUseEquipment(info);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/use/{ueid}",method = RequestMethod.DELETE)
    public Result stopUseEquipmentById(@PathVariable("ueid") String ueid ){
        equipmentService.stopUseEquipmentById(ueid);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/use/",method = RequestMethod.PUT)
    public Result updateUseEquipment(@RequestBody UseEquipmentInfo info){

        equipmentService.updateUseEquipment(info);

        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/use/",method = RequestMethod.GET)
    public Result findAllUseEquipment(){
        List<UseEquipmentInfo> useEquipmentList = equipmentService.findAllUseEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(useEquipmentList);
        return result;
    }

    @RequestMapping(value = "/use/{status}",method = RequestMethod.GET)
    public Result findStatusUseEquipment(@PathVariable(value = "status") String status){
        List<UseEquipmentInfo> useEquipment =  equipmentService.findStatusUseEquipment(status);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(useEquipment);
        return result;
    }
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public Result findEquipmentGroup(){
        Result  result= equipmentService.findEquipmentGroup();
        return result;
    }
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public Result addGroupToEquipment(@RequestBody GroupDict group){
        equipmentService.addGroupToEquipment(group);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public Result updateGroupToEquipment(@RequestBody GroupDict group){
        equipmentService.updateGroupToEquipment(group);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public void getExcel(HttpServletResponse response) throws IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {
        List<Equipment> allEquipment = equipmentService.findAllEquipment();

//        Workbook workbook = excelUtil.exportExcel(allEquipment, "test");
//        excelUtil.setHeard(workbook,response);
        ExcelUtils excel = FileFactory.getExcel();
        excel.export("equipment",allEquipment,response);
    }
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public Result importExcel(MultipartFile file) throws IOException, InvalidFormatException {
        excelUtil.importExcel(file);
        return new Result(ResultCode.SUCCESS);
    }
}
