package people.cn.dao;
import org.apache.ibatis.annotations.*;
import people.cn.bean.Equipment;
import people.cn.bean.GroupDict;
import people.cn.bean.UseEquipmentInfo;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午2:32
 * @description: 设备持久层
 */
@Mapper
public interface EquipmentMapper {

    List<Equipment> findAllEquipment();


    Equipment findEquipmentById(String id);

    /**
     * 通过设备类型查找
     * @param gid
     * @return
     */

    List<Equipment> findEquipmentByGroupId(String gid);

    void updateEquipment(Equipment equipment);

    void saveEquipment(Equipment equipment);

    void saveUseEquipment(UseEquipmentInfo info);

    void stopUseEquipmentById(String ueid);

    void updateUseEquipment(UseEquipmentInfo info);


    List<UseEquipmentInfo> findAllUseEquipment();


    List<UseEquipmentInfo> findStatusUseEquipment(String status);

    /**
     * 分配设备后改变设备分配状态 0：正在使用
     *
     */
    void changeMetrics(String eid);

    /**
     * 分配设备后启用设备并改变涉笔分配状态 metrics 0 :用户正在使用 status 0 ：设备已启用
     */
    void changeMetricsAndStatus(String eid);

    List<GroupDict> findEquipmentGroup();

    /**
     * 新增设备分类
     * @param group
     */
    void addGroupToEquipment(GroupDict group);

    /**
     * 更新设备分类
     * @param group
     */
    void updateGroupToEquipment(GroupDict group);

    /**
     * 通过模糊查找设备id
     */
    List<Equipment> likeEquepment(String code);

    List<Equipment> findEquipmentByCodeAndBrand(String brand, String code);

    /**
     * 设备状态故障
     * @param id
     * @return
     */
    void faultEquipment(String id);

    /**
     * 当设备停用时，变更分配状态
     * @param id
     */
    void stopMetrics(String id);

    List<Equipment> findAllEquipmentMetricsIsNot();
}
