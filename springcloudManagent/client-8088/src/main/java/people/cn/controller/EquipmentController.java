package people.cn.controller;

import cn.people.domain.Equipment;
import cn.people.domain.GroupDict;
import cn.people.domain.UseEquipmentInfo;
import cn.people.service.IEquipmentService;
import cn.people.utils.common.ExcelUtil;
import cn.people.utils.common.ExcelUtils;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import cn.people.utils.factory.FileFactory;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation(value = "查找所有设备")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Result findAllEquipment(){
        List<Equipment> EquipmentList = equipmentService.findAllEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(EquipmentList);
        return result;
    }
    @ApiOperation(value = "获取未使用的设备")
    @RequestMapping(value = "metrics",method = RequestMethod.GET)
    public Result findAllEquipmentMetrics(){
        List<Equipment> equipments = equipmentService.findAllEquipmentMetricsIsNot();
        Result success = Result.SUCCESS();
        success.setData(equipments);
        return success;
    }

    @ApiOperation(value = "查找同类型的设备")
    @RequestMapping(value = "/{gid}",method = RequestMethod.GET)
    public Result findEquipmentByGroupId(@PathVariable("gid") String gid){
        System.out.println(gid);
        List<Equipment> equipment = equipmentService.findEquipmentByGroupId(gid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(equipment);
        return result;
    }

    @ApiOperation(value = "更新设备")
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

    @ApiOperation(value = "新增设备")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Result saveEquipment(@RequestBody Equipment equipment){
        equipmentService.saveEquipment(equipment);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "新增设备使用单")
    @RequestMapping(value = "/use",method = RequestMethod.POST)
    public Result saveUseEquipment(@RequestBody UseEquipmentInfo info){

        equipmentService.saveUseEquipment(info);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value ="停用设备使用")
    @RequestMapping(value = "/use/{ueid}",method = RequestMethod.DELETE)
    public Result stopUseEquipmentById(@PathVariable("ueid") String ueid ){
        equipmentService.stopUseEquipmentById(ueid);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value ="更新设备使用单信息")
    @RequestMapping(value = "/use/",method = RequestMethod.PUT)
    public Result updateUseEquipment(@RequestBody UseEquipmentInfo info){

        equipmentService.updateUseEquipment(info);

        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "查询所有设备使用单")
    @RequestMapping(value = "/use/",method = RequestMethod.GET)
    public Result findAllUseEquipment(){
        List<UseEquipmentInfo> useEquipmentList = equipmentService.findAllUseEquipment();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(useEquipmentList);
        return result;
    }

    @ApiOperation(value = "查询正在使用设备单，默认为1")
    @RequestMapping(value = "/use/{status}",method = RequestMethod.GET)
    public Result findStatusUseEquipment(@PathVariable(value = "status") String status){
        List<UseEquipmentInfo> useEquipment =  equipmentService.findStatusUseEquipment(status);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(useEquipment);
        return result;
    }
    @ApiOperation(value = "查找设备分类")
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public Result findEquipmentGroup(){
        Result  result= equipmentService.findEquipmentGroup();
        return result;
    }
    @ApiOperation(value = "新增设备分类")
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    public Result addGroupToEquipment(@RequestBody GroupDict group){
        equipmentService.addGroupToEquipment(group);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "更新设备分类")
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public Result updateGroupToEquipment(@RequestBody GroupDict group){
        equipmentService.updateGroupToEquipment(group);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "下载文件")
    @RequestMapping(value = "/file",method = RequestMethod.GET)
    public void getExcel(HttpServletResponse response) throws IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {
        List<Equipment> allEquipment = equipmentService.findAllEquipment();

//        Workbook workbook = excelUtil.exportExcel(allEquipment, "test");
//        excelUtil.setHeard(workbook,response);
        ExcelUtils excel = FileFactory.getExcel();
        excel.export("equipment",allEquipment,response);
    }
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/file",method = RequestMethod.POST)
    public Result importExcel(MultipartFile file) throws IOException, InvalidFormatException {
        excelUtil.importExcel(file);
        return new Result(ResultCode.SUCCESS);
    }
}
