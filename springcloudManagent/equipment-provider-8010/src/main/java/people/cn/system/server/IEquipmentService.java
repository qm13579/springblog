package people.cn.system.server;

import people.cn.bean.Equipment;
import people.cn.bean.GroupDict;
import people.cn.bean.UseEquipmentInfo;
import people.cn.common.Result;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  上午11:26
 * @description: 设备情况
 */
public interface IEquipmentService {
    /**
     * 获取全部设备信息
     * @return
     */
    public List<Equipment> findAllEquipment();

    /**
     * 通过设备分类信息id获取设备信息
     * @param gid
     * @return
     */
    public List<Equipment> findEquipmentByGroupId(String gid);

    /**
     * 更新当前设备信息
     * @param equipment
     * @return
     */
    public boolean updateEquipment(Equipment equipment);

    /**
     * 新增设备信息
     * @param equipment
     */
    public void saveEquipment(Equipment equipment);

    /**
     * 新增使用设备信息
     * @param info
     */
    public void saveUseEquipment(UseEquipmentInfo info);

    /**
     * 停用设备
     * @param ueid
     */
    public void stopUseEquipmentById(String ueid);

    /**
     * 更新设备使用信息
     * @param info
     */
    public void updateUseEquipment(UseEquipmentInfo info);

    /**
     * 查询所有设备使用信息
     * @return
     */
    public List<UseEquipmentInfo> findAllUseEquipment();

    /**
     * 查询正在使用的设备信息
     * @param status
     * @return
     */
    public List<UseEquipmentInfo> findStatusUseEquipment(String status);


    Result findEquipmentGroup();

    /**
     * 增加设备分类
     * @param group
     */
    void addGroupToEquipment(GroupDict group);

    /**
     * 更新设备分类
     * @param group
     */
    void updateGroupToEquipment(GroupDict group);

    /**
     * 获取未使用的设备
     */
    List<Equipment> findAllEquipmentMetricsIsNot();
}
