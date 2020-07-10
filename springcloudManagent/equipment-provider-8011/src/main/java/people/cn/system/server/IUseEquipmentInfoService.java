package people.cn.system.server;

import people.cn.bean.UseEquipmentInfo;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/21  下午1:59
 * @description: 使用设备的业务成接口
 */
public interface IUseEquipmentInfoService {
    /**
     * 查找当前正在使用的设备单
     * @return
     */
    List<UseEquipmentInfo> findAllUseEquipment();

    /**
     * 添加新的设备单
     * @param info
     */
    void addUseEquipment(UseEquipmentInfo info);

    /**
     * 更新设备单的信息
     * @param info
     */
    boolean updateUseEquipment(UseEquipmentInfo info);

    /**
     * 查找当前用户的设备单
     * @param uid
     * @return
     */
    List<UseEquipmentInfo> findUseEquipmentByUserId(String uid);

    /**
     * 增加设备使用，并启动设备
     * @param info
     */
    boolean metricsUseEquipment(UseEquipmentInfo info);

    List<UseEquipmentInfo> findHistoryUseEquipment();

    /**
     * 根据设备查看当前使用信息
     * @param eid
     * @return
     */
    UseEquipmentInfo findUseEquipmentByEId(String eid);
}
