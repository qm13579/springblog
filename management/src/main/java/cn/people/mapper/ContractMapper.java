package cn.people.mapper;

import cn.people.domain.ContractEquipment;
import cn.people.domain.ContractMaintenance;
import cn.people.domain.DraftContract;
import cn.people.domain.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/17  下午8:42
 * @description:
 */
public interface ContractMapper {

    @Insert("insert into equipment_contract(e_c_id,contract_name,start_time,status,party_a,party_a_legal," +
            "party_a_linkman,party_a_linkman_mobile,party_b,party_b_legal,party_b_linkman,party_b_linkman_mobile,money) " +
            "values (#{cid},#{contractName},#{startTime},#{status},#{partyA},#{partyALegal},#{partyALinkman},#{partyALinkmanMobile}" +
            ",#{partyB},#{partyBLegal},#{partyBLinkman},#{partyBLinkmanMobile},#{money})")
    void insertEquipmentContract(ContractEquipment contractEquipment);

    @Update("update equipment_contract set contract_name=#{contractName},start_time=#{startTime},party_a= #{partyA} where e_c_id = #{cid}")
    void updateEquipmentContract(ContractEquipment contractEquipment);

    @Update("update equipment_contract set status=1 where e_c_id=#{cid}")
    void stopEquipmentContract(String cid);

    @Update("update equipment_contract set file=#{fileId} where e_c_id=#{cid}")
    void uploadEquipmentContract(@Param("fileId") String fileId, @Param("cid") String cid);

    @Select("select * from equipment_contract")
    @Results({
            @Result(property = "cid",column = "e_c_id"),
            @Result(property = "contractName",column = "contract_name"),
            @Result(property = "startTime",column = "start_time"),
            @Result(property = "partyA",column = "party_a"),
            @Result(property = "partyALegal",column = "party_a_legal"),
            @Result(property = "partyALinkman",column = "party_a_linkman"),
            @Result(property = "partyALinkmanMobile",column = "party_a_linkman_mobile"),
            @Result(property = "partyB",column = "party_b"),
            @Result(property = "partyBLegal",column = "party_b_legal"),
            @Result(property = "partyBLinkman",column = "party_b_linkman"),
            @Result(property = "partyBLinkmanMobile",column = "party_b_linkman_mobile"),

    })
    List<ContractEquipment> findEquipmentContract();

    @Insert("insert into maintenance_contract(m_c_id,contract_name,start_time,end_time,status,party_a,party_a_legal," +
            "party_a_linkman,party_a_linkman_mobile,party_b,party_b_legal,party_b_linkman,party_b_linkman_mobile,money) " +
            "values (#{cid},#{contractName},#{startTime},#{endTime},#{status},#{partyA},#{partyALegal},#{partyALinkman},#{partyALinkmanMobile}" +
            ",#{partyB},#{partyBLegal},#{partyBLinkman},#{partyBLinkmanMobile},#{money})")
    void insertMaintenance(ContractMaintenance maintenance);

    @Update("update maintenance_contract set contract_name=#{contractName} where m_c_id = #{cid}")
    void updateMaintenance(ContractMaintenance maintenance);

    @Update("update maintenance_contract set status=1 where m_c_id = #{cid}")
    void stopMaintenance(String cid);

    @Select("select * from maintenance_contract")
    @Results({
            @Result(property = "cid",column = "m_c_id"),
            @Result(property = "contractName",column = "contract_name"),
            @Result(property = "startTime",column = "start_time"),
            @Result(property = "endTime",column = "end_time"),
            @Result(property = "partyA",column = "party_a"),
            @Result(property = "partyALegal",column = "party_a_legal"),
            @Result(property = "partyALinkman",column = "party_a_linkman"),
            @Result(property = "partyALinkmanMobile",column = "party_a_linkman_mobile"),
            @Result(property = "partyB",column = "party_b"),
            @Result(property = "partyBLegal",column = "party_b_legal"),
            @Result(property = "partyBLinkman",column = "party_b_linkman"),
            @Result(property = "partyBLinkmanMobile",column = "party_b_linkman_mobile"),
    })
    List<ContractMaintenance> findMaintenanceContract();

    @Update("update maintenance_contract set file=#{fileId} where m_c_id=#{cid}")
    void uploadMaintenanceContract(@Param("fileId") String fileId, @Param("cid") String cid);

    /**
     * 保存hetong
     * @param draft
     */
    @Insert("insert into draft_contract (id,context,status,title) values(#{id},#{context},#{status},#{title})")
    void uploadDraftContract(DraftContract draft);

    /**
     * 通过id查找合同
     * @param id
     * @return
     */
    @Select("select * from draft_contract where id=#{id}")
    DraftContract downloadDraftContract(String id);

    /**
     * 查找范本合同
     * @return
     */
    @Select("select * from draft_contract where status=0")
    List<DraftContract> getDraftContractStatus();

    /**
     * 查找当前合同中的设备信息
     * @param cid
     * @return
     */
    @Select("select * from equipment where id in (select equipment from equipment_m_contract where contract=#{cid}) ")
    List<Equipment> getAssignEquipment(String cid);

    /**
     * 在设备合同下插入新的设备
     * @param cid
     * @param equipmentId
     */
    @Insert("insert into equipment_m_contract (equipment,contract) values(#{equipmentId},#{cid})")
    void insertAssignEquipment(@Param("cid") String cid, @Param("equipmentId") String equipmentId);

    /**
     * 删除设备合同下的设备
     * @param cid
     * @param
     */
    @Delete("delete from equipment_m_contract where id=#{cid}")
    void deleteAssignEquipment(String cid);
}
