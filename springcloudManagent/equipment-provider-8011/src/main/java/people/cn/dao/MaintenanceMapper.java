package people.cn.dao;


import org.apache.ibatis.annotations.Mapper;
import people.cn.bean.Maintenance;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/22  上午10:33
 * @description: 维保持久层
 */
@Mapper
public interface MaintenanceMapper {
    /**
     *
     * @return
     */

    List<Maintenance> findAllMaintenance();

    /**
     *
     * @param eid
     * @return
     */

    List<Maintenance> findAllMaintenanceByEquipment(String eid);

    /**
     * 插入维保记录
     * @param maintenance
     */
    void insertMaintenance(Maintenance maintenance);

    /**
     * 工单状态由故障更新为正常
     * @param equipmentId
     */
    void changeStatus(String equipmentId);
}
