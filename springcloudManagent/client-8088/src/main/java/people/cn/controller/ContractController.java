package people.cn.controller;

import cn.people.domain.ContractEquipment;
import cn.people.domain.ContractMaintenance;
import cn.people.domain.DraftContract;
import cn.people.domain.Equipment;
import cn.people.service.ContractService;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import cn.people.utils.factory.PdfUtilFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 合同处理类
 * @author : FENGZHI
 * create at:  2020/3/16  下午2:47
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController {


    @ApiOperation(value = "查看")
    @RequestMapping(value = "/equipment",method = RequestMethod.GET)
    public Result findEquipmentContract(){
        List<ContractEquipment> contractEquipment = contractService.findEquipmentContract();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(contractEquipment);
        return result;
    }

    @ApiOperation(value = "创建设备合同")
    @RequestMapping(value = "/equipment",method = RequestMethod.POST)
    public Result insertEquipmentContract(@RequestBody ContractEquipment contractEquipment){
        contractService.insertEquipmentContract(contractEquipment);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "更改设备合同信息")
    @RequestMapping(value = "/equipment",method = RequestMethod.PUT)
    public Result updateEquipmentContract(ContractEquipment contractEquipment){
        contractService.updateEquipmentContract(contractEquipment);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "合同过期")
    @RequestMapping(value = "/equipment/{cid}",method = RequestMethod.DELETE)
    public Result stopEquipmentContract(@PathVariable("cid") String cid){
        contractService.stopEquipmentContract(cid);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "下载设备合同")
    @RequestMapping(value = "/download/{fileId}",method = RequestMethod.GET)
    public void downloadContractFile(HttpServletResponse response,@PathVariable("fileId") String fileId) throws IOException {

        try{
            contractService.downloadContractFile(fileId,response);
        }catch (Exception e){
            log.error("download error:"+e.toString());
        }
    }

    @ApiOperation(value = "上传设备合同")
    @RequestMapping(value = "/equipment/{cid}",method = RequestMethod.POST)
    public Result uploadEquipmentContract(MultipartFile file,@PathVariable("cid") String cid){
        log.info(cid);
        try {
            PdfUtilFactory pdfUtilFactory = new PdfUtilFactory();
            Boolean aBoolean = pdfUtilFactory.getUserPdfUtil().checkFileName(file.getOriginalFilename());
            if (!aBoolean){
                Result result = new Result(ResultCode.ERROR);
                result.setMessage("文件格式必须为pdf");
                return result;
            }
            contractService.uploadEquipmentContract(file,cid);
        }catch (Exception e){
            log.error("file error:"+e.toString());
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "文件预览")
    @RequestMapping(value = "/preview/{fileId}",method = RequestMethod.GET)
    public Result previewFile(HttpServletResponse response,@PathVariable("fileId") String fileId){
        try {
            contractService.previewFile(response,fileId);
            return null;
        }catch (Exception e){
            log.error(e.toString());
            Result result = new Result(ResultCode.ERROR);
            result.setMessage("预览失败，请稍后重试");
            return result;
        }
    }

    @ApiOperation(value = "查找所有维保合同")
    @RequestMapping(value = "/maintenance",method = RequestMethod.GET)
    public Result findMaintenanceContract(){
        Result result = new Result(ResultCode.SUCCESS);
        List<ContractMaintenance> maintenances = contractService.findMaintenanceContract();
        result.setData(maintenances);
        return result;
    }

    @ApiOperation(value = "创建维保合同")
    @RequestMapping(value = "/maintenance",method = RequestMethod.POST)
    public Result insertMaintenance(@RequestBody ContractMaintenance maintenance){
        contractService.insertMaintenance(maintenance);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "更新维保合同")
    @RequestMapping(value = "/maintenance",method = RequestMethod.PUT)
    public  Result updateMaintenance(@RequestBody ContractMaintenance maintenance){
        contractService.updateMaintenance(maintenance);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "停止维保合同")
    @RequestMapping(value = "/maintenance/{cid}",method = RequestMethod.DELETE)
    public Result stopMaintenance(@PathVariable("cid") String cid){
        contractService.stopMaintenance(cid);
        return new Result(ResultCode.SUCCESS);
    }
    @ApiOperation(value = "上传维保合同")
    @RequestMapping(value = "/maintenance/{cid}",method = RequestMethod.POST)
    public Result uploadMaintenanceFile(MultipartFile file,@PathVariable("cid") String cid){
        try {
            PdfUtilFactory pdfUtilFactory = new PdfUtilFactory();
            Boolean aBoolean = pdfUtilFactory.getUserPdfUtil().checkFileName(file.getOriginalFilename());
            if (!aBoolean){
                Result result = new Result(ResultCode.ERROR);
                result.setMessage("文件格式必须为pdf");
                return result;
            }
            contractService.uploadMaintenanceContract(file,cid);

        }catch (Exception e){
            log.error(e.toString());
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "上传合同")
    @RequestMapping(value = "/draft",method = RequestMethod.POST)
    public Result uploadDraftContract(@RequestBody DraftContract draft){
        contractService.uploadDraftContract(draft);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "获取合同")
    @RequestMapping(value = "/draft/{id}",method = RequestMethod.GET)
    public Result downloadDraftContract(@PathVariable("id") String id){
        DraftContract contract = contractService.downloadDraftContract(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(contract);
        return result;
    }
    @ApiOperation(value = "获取合同范本")
    @RequestMapping(value = "/draft/status",method = RequestMethod.GET)
    public Result getDraftContractStatus(){
        List<DraftContract> contracts= contractService.getDraftContractStatus();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(contracts);
        return result;
    }

    @ApiOperation(value = "获取设备合同的所属设备")
    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.GET)
    public Result getAssignEquipment(@PathVariable("cid") String cid){
        //获取当前合同中的设备信息
        List<Equipment> contractEquipments= contractService.getAssignEquipment(cid);
        Result result = new Result(ResultCode.SUCCESS);
        if (contractEquipments != null){
            result.setData(contractEquipments);
        }
        return result;
    }

    @ApiOperation(value = "插入新的设备到当合同中")
    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.POST)
    public Result insertAssignEquipment(@PathVariable("cid") String cid,@RequestBody List<String> list ){
        contractService.insertAssignEquipment(cid,list);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "移除设备合同中的设备")
    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.DELETE)
    public Result deleteAssignEquipment(@PathVariable("cid") String cid,@RequestBody List<String> list ){
        contractService.deleteAssignEquipment(cid,list);
        return new Result(ResultCode.SUCCESS);
    }

    @ApiOperation(value = "更新合同中的涉笔")
    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.PUT)
    public Result updateAssignEquipment(@PathVariable("cid") String cid,@RequestBody List<String> list){
        contractService.updateAssignEquipment(cid,list);
        return Result.SUCCESS();

    }
}
