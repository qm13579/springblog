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
    /**
     * 查询所有的设备
     * @return
     */
    List<Equipment> findAllEquipment();

    /**
     * 通过id查询设备
     * @param id
     * @return
     */
    Equipment findEquipmentById(@Param("id") String id);

    /**
     * 通过设备类型查找
     * @param gid
     * @return
     */
    List<Equipment> findEquipmentByGroupId(@Param("gid") String gid);

    /**
     * 更新设备
     * @param equipment
     */
    void updateEquipment(Equipment equipment);

    /**
     * 添加设备
     * @param equipment
     */
    void saveEquipment(Equipment equipment);

    /**
     * 添加使用信息
     * @param info
     */
    void saveUseEquipment(UseEquipmentInfo info);

    /**
     * 停止使用某各设备
     * @param ueid
     */
    void stopUseEquipmentById(@Param("ueid") String ueid);

    /**
     * 更新使用设备信息
     * @param info
     */
    void updateUseEquipment(UseEquipmentInfo info);

    /**
     * 获取全部的设备使用信息
     * @return
     */
    List<UseEquipmentInfo> findAllUseEquipment();

    /**
     * 获取正在使用的设备
     * @param status
     * @return
     */
    List<UseEquipmentInfo> findStatusUseEquipment(@Param("status") String status);

    /**
     * 分配设备后改变设备分配状态 0：正在使用
     * @param eid
     */
    void changeMetrics(@Param("eid") String eid);

    /**
     * 分配设备后启用设备并改变涉笔分配状态 metrics 0 :用户正在使用 status 0 ：设备已启用
     * @param eid
     */
    void changeMetricsAndStatus(@Param("eid") String eid);

    /**
     * 获取全部的设备分类
     * @return
     */
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
     * @param code
     * @return
     */
    List<Equipment> likeEquepment(String code);

    /**
     *
     * @param brand
     * @param code
     * @return
     */
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
