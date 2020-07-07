package people.cn.dao;


import org.apache.ibatis.annotations.*;
import people.cn.bean.ContractEquipment;
import people.cn.bean.ContractMaintenance;
import people.cn.bean.DraftContract;
import people.cn.bean.Equipment;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/17  下午8:42
 * @description:
 */
@Mapper
public interface ContractMapper {

    void insertEquipmentContract(ContractEquipment contractEquipment);

    void updateEquipmentContract(ContractEquipment contractEquipment);

    void stopEquipmentContract(String cid);

    void uploadEquipmentContract(@Param("fileId") String fileId, @Param("cid") String cid);


    List<ContractEquipment> findEquipmentContract();

    void insertMaintenance(ContractMaintenance maintenance);

    void updateMaintenance(ContractMaintenance maintenance);

    void stopMaintenance(String cid);

    List<ContractMaintenance> findMaintenanceContract();

    void uploadMaintenanceContract(@Param("fileId") String fileId, @Param("cid") String cid);

    /**
     * 保存hetong
     * @param draft
     */
    void uploadDraftContract(DraftContract draft);

    /**
     * 通过id查找合同
     * @param id
     * @return
     */
    DraftContract downloadDraftContract(String id);

    /**
     * 查找范本合同
     * @return
     */
    List<DraftContract> getDraftContractStatus();

    /**
     * 查找当前合同中的设备信息
     * @param cid
     * @return
     */
    List<Equipment> getAssignEquipment(String cid);

    /**
     * 在设备合同下插入新的设备
     * @param cid
     * @param equipmentId
     */
    void insertAssignEquipment(@Param("cid") String cid, @Param("equipmentId") String equipmentId);

    /**
     * 删除设备合同下的设备
     * @param cid
     * @param
     */
    void deleteAssignEquipment(String cid);
}
