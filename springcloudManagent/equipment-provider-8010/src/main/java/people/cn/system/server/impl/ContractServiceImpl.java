package people.cn.system.server.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;
import people.cn.bean.ContractEquipment;
import people.cn.bean.ContractMaintenance;
import people.cn.bean.DraftContract;
import people.cn.bean.Equipment;
import people.cn.common.IdWorker;
import people.cn.common.factory.PdfUtilFactory;
import people.cn.dao.ContractMapper;
import people.cn.system.server.ContractService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/16  下午3:48
 * @description:
 */
@Slf4j
@Service
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractMapper contractMapper;
    @Autowired
    private IdWorker idWorker;

    @Override
    public void insertEquipmentContract(ContractEquipment contractEquipment){
        contractEquipment.setStatus(0);
        contractEquipment.setCid(idWorker.nextId()+"");
        contractMapper.insertEquipmentContract(contractEquipment);
    }

    @Override
    public void updateEquipmentContract(ContractEquipment contractEquipment) {
        contractMapper.updateEquipmentContract(contractEquipment);
    }

    @Override
    public void stopEquipmentContract(String cid) {
        contractMapper.stopEquipmentContract(cid);
    }

    @Override
    public void uploadEquipmentContract(MultipartFile file, String cid) throws IOException {
//        String path = "/";
        String path = ResourceUtils.getURL("classpath:").getPath();

        log.info("file path:"+path);
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
        String filename = file.getOriginalFilename();
        String substring = filename.substring(filename.lastIndexOf("."));
        String fileId = idWorker.nextId() + ""+substring;
        File desc = new File(path + fileId);
        file.transferTo(desc);

        contractMapper.uploadEquipmentContract(fileId,cid);

    }

    @Override
    public void downloadContractFile(String fileId,HttpServletResponse response) throws IOException {

        String path = "/tmp/"+"file/";
        byte[] bytes = new byte[1024];
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment;fileName=file.xlsx");
        ServletOutputStream os = response.getOutputStream();
        FileInputStream fis = null;
        BufferedInputStream bis= null;
        try{
            fis = new FileInputStream(new File(path+fileId));
            bis = new BufferedInputStream(fis);
            int i = bis.read(bytes);
            while (i != -1){
                os.write(bytes,0,i);
                os.flush();
                i = bis.read(bytes);
            }
        }catch (Exception e){
            log.error("file download error:"+e.toString());
        }finally {
            fis.close();
            bis.close();
            os.close();
        }
    }

    @Override
    public void previewFile(HttpServletResponse response, String fileId) throws IOException {
        PdfUtilFactory pdfUtilFactory = new PdfUtilFactory();

        String filePath = ResourceUtils.getURL("classpath:").getPath()+fileId;
//        String filePath = "./tmp/"+"file/"+fileId;
        pdfUtilFactory.getUserPdfUtil().preview(filePath,response);
    }

    @Override
    public List<ContractEquipment> findEquipmentContract() {
        return contractMapper.findEquipmentContract();
    }

    @Override
    public void insertMaintenance(ContractMaintenance maintenance) {
        maintenance.setCid(idWorker.nextId()+"");
        maintenance.setStatus(0);
        contractMapper.insertMaintenance(maintenance);
    }

    @Override
    public void updateMaintenance(ContractMaintenance maintenance) {
        contractMapper.updateMaintenance(maintenance);
    }

    @Override
    public List<ContractMaintenance> findMaintenanceContract() {
        return contractMapper.findMaintenanceContract();
    }

    @Override
    public void stopMaintenance(String cid) {
        contractMapper.stopMaintenance(cid);
    }

    @Override
    public void uploadMaintenanceContract(MultipartFile file, String cid) throws IOException {

        String path = ResourceUtils.getURL("classpath:").getPath();
        log.info("file path:"+path);
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
        System.out.println("file create");
        String filename = file.getOriginalFilename();
        String substring = filename.substring(filename.lastIndexOf("."));
        String fileId = idWorker.nextId() + ""+substring;
        File desc = new File(path + fileId);
        file.transferTo(desc);
        contractMapper.uploadMaintenanceContract(fileId,cid);

    }

    @Override
    public void uploadDraftContract(DraftContract draft) {
        log.info(draft.toString());
        //富文本格式用过工具类对特殊字符进行转义
        draft.setContext(HtmlUtils.htmlEscape(draft.getContext()));
        draft.setId(idWorker.nextId()+"");
        //1：已制作完成的合同
        draft.setStatus(1);
        log.info(draft.toString());
        contractMapper.uploadDraftContract(draft);
    }

    @Override
    public DraftContract downloadDraftContract(String id) {
        DraftContract contract = contractMapper.downloadDraftContract(id);
        //通过工具类对转义后的字符进行解析
        contract.setContext(HtmlUtils.htmlUnescape(contract.getContext()));
        log.info(contract.getContext());
        return contract;
    }

    @Override
    public List<DraftContract> getDraftContractStatus() {
        return contractMapper.getDraftContractStatus();
    }

    @Override
    public List<Equipment> getAssignEquipment(String cid) {
        return contractMapper.getAssignEquipment(cid);
    }

    @Override
    public void insertAssignEquipment(String cid, List<String> list) {
        for (String equipmentId :list) {
            contractMapper.insertAssignEquipment(cid,equipmentId);
        }
    }

    @Override
    public void deleteAssignEquipment(String cid, List<String> list) {
        contractMapper.deleteAssignEquipment(cid);
    }

    @Override
    public void updateAssignEquipment(String cid, List<String> list) {
        //先删除后更新
        contractMapper.deleteAssignEquipment(cid);
        for (String string :list) {
            contractMapper.insertAssignEquipment(cid,string);
        }

    }
}