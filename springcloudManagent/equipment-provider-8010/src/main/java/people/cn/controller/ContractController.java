package people.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import people.cn.bean.ContractEquipment;
import people.cn.bean.ContractMaintenance;
import people.cn.bean.DraftContract;
import people.cn.bean.Equipment;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.common.factory.PdfUtilFactory;
import people.cn.server.ContractService;

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

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/equipment",method = RequestMethod.GET)
    public Result findEquipmentContract(){
        List<ContractEquipment> contractEquipment = contractService.findEquipmentContract();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(contractEquipment);
        return result;
    }

    @RequestMapping(value = "/equipment",method = RequestMethod.POST)
    public Result insertEquipmentContract(@RequestBody ContractEquipment contractEquipment){
        contractService.insertEquipmentContract(contractEquipment);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/equipment",method = RequestMethod.PUT)
    public Result updateEquipmentContract(ContractEquipment contractEquipment){
        contractService.updateEquipmentContract(contractEquipment);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/equipment/{cid}",method = RequestMethod.DELETE)
    public Result stopEquipmentContract(@PathVariable("cid") String cid){
        contractService.stopEquipmentContract(cid);
        return new Result(ResultCode.SUCCESS);
    }
    @RequestMapping(value = "/download/{fileId}",method = RequestMethod.GET)
    public void downloadContractFile(HttpServletResponse response,@PathVariable("fileId") String fileId) throws IOException {

        try{
            contractService.downloadContractFile(fileId,response);
        }catch (Exception e){
            log.error("download error:"+e.toString());
        }
    }

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

    @RequestMapping(value = "/maintenance",method = RequestMethod.GET)
    public Result findMaintenanceContract(){
        Result result = new Result(ResultCode.SUCCESS);
        List<ContractMaintenance> maintenances = contractService.findMaintenanceContract();
        result.setData(maintenances);
        return result;
    }

    @RequestMapping(value = "/maintenance",method = RequestMethod.POST)
    public Result insertMaintenance(@RequestBody ContractMaintenance maintenance){
        contractService.insertMaintenance(maintenance);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/maintenance",method = RequestMethod.PUT)
    public  Result updateMaintenance(@RequestBody ContractMaintenance maintenance){
        contractService.updateMaintenance(maintenance);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/maintenance/{cid}",method = RequestMethod.DELETE)
    public Result stopMaintenance(@PathVariable("cid") String cid){
        contractService.stopMaintenance(cid);
        return new Result(ResultCode.SUCCESS);
    }

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

    @RequestMapping(value = "/draft",method = RequestMethod.POST)
    public Result uploadDraftContract(@RequestBody DraftContract draft){
        contractService.uploadDraftContract(draft);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/draft/{id}",method = RequestMethod.GET)
    public Result downloadDraftContract(@PathVariable("id") String id){
        DraftContract contract = contractService.downloadDraftContract(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(contract);
        return result;
    }
    @RequestMapping(value = "/draft/status",method = RequestMethod.GET)
    public Result getDraftContractStatus(){
        List<DraftContract> contracts= contractService.getDraftContractStatus();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(contracts);
        return result;
    }

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

    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.POST)
    public Result insertAssignEquipment(@PathVariable("cid") String cid,@RequestBody List<String> list ){
        contractService.insertAssignEquipment(cid,list);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.DELETE)
    public Result deleteAssignEquipment(@PathVariable("cid") String cid,@RequestBody List<String> list ){
        contractService.deleteAssignEquipment(cid,list);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/assign/equipment/{cid}",method = RequestMethod.PUT)
    public Result updateAssignEquipment(@PathVariable("cid") String cid,@RequestBody List<String> list){
        contractService.updateAssignEquipment(cid,list);
        return Result.SUCCESS();

    }
}
