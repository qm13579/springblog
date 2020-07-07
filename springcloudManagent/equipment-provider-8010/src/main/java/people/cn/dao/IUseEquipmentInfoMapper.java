package people.cn.dao;


import org.apache.ibatis.annotations.*;
import people.cn.bean.UseEquipmentInfo;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/21  上午10:44
 * @description: 用户设备使用信息
 */
@Mapper
public interface IUseEquipmentInfoMapper {

    /**
     * 查询使用信息及当前使用设备
     * @return
     */

    List<UseEquipmentInfo> findAllUseEquipment();

    /**
     * 根据用户id查询用户所有使用设备记录
     * @param uid
     * @return
     */
    @Select("select * from use_equipment_info where user_id=#{uid}")
    List<UseEquipmentInfo> findAllUseEquipmentByUserId(String uid);

    /**
     * 添加工单id为自增
     * @param info
     */

    public void addUseEquipment(UseEquipmentInfo info);

    /**
     * 更新使用设备单
     * @param info
     */
    public void updateUseEquipment(UseEquipmentInfo info);

    /**
     * 查找历史工单
     * @return
     */

    List<UseEquipmentInfo> findHistoryUseEquipment();

    /**
     * 通过设备id查询工单在用
     * @param eid
     * @return
     */

    UseEquipmentInfo findUseEquipmentByEquipmentByid(String eid);

    /**
     * 停用工单
     * @param id
     * @param endDate
     */
    void stopUseEquipmentById(@Param("id") int id, @Param("endDate") String endDate);

    /**
     * 设备停用，删除使用人
     * @param eid
     */
    void stopUseEquipmentByEId(String eid);

    /**
     * 根据用户查找使用信息
     * @param eid
     * @return
     */

    UseEquipmentInfo findUseEquipmentByEId(String eid);
}
