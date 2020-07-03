package people.cn.server;


import org.springframework.web.multipart.MultipartFile;
import people.cn.bean.ContractEquipment;
import people.cn.bean.ContractMaintenance;
import people.cn.bean.DraftContract;
import people.cn.bean.Equipment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/16  下午3:40
 * @description:
 */
public interface ContractService {

    /**
     * 新增合同及文件
     * @param contractEquipment
     */
    void insertEquipmentContract(ContractEquipment contractEquipment);

    /**
     * 更新合同及文件
     * @param contractEquipment
     */
    void updateEquipmentContract(ContractEquipment contractEquipment);

    /**
     * 停用合同
     * @param cid
     */
    void stopEquipmentContract(String cid);

    /**
     * 文件上传
     * @param file 文件
     * @param cid 合同id
     * @throws IOException
     */
    void uploadEquipmentContract(MultipartFile file, String cid) throws IOException;

    /**
     * 文件下载
     * @param fileId 文件id.格式
     * @param response
     * @throws IOException
     */
    void downloadContractFile(String fileId, HttpServletResponse response) throws IOException;

    /**
     * 预览文件
     * @param response
     * @param fileId 文件id.格式
     */
    void previewFile(HttpServletResponse response, String fileId) throws IOException;

    List<ContractEquipment> findEquipmentContract();

    void insertMaintenance(ContractMaintenance maintenance);

    void updateMaintenance(ContractMaintenance maintenance);

    List<ContractMaintenance> findMaintenanceContract();

    void stopMaintenance(String cid);

    void uploadMaintenanceContract(MultipartFile file, String cid) throws IOException;

    void uploadDraftContract(DraftContract draft);

    DraftContract downloadDraftContract(String id);

    List<DraftContract> getDraftContractStatus();

    /**
     * 获取当前合同下的设备信息
     * @param cid
     * @return
     */
    List<Equipment> getAssignEquipment(String cid);

    /**
     * 在设备合同下新增设备
     * @param cid
     * @param list
     */
    void insertAssignEquipment(String cid, List<String> list);

    /**
     * 删除设备合同下的设备
     * @param cid
     * @param list
     */
    void deleteAssignEquipment(String cid, List<String> list);

    /**
     * 更新合同下的设备
     * @param cid
     * @param list
     */
    void updateAssignEquipment(String cid, List<String> list);
}
