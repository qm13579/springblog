package cn.people.service;

import cn.people.domain.Maintenance;
import cn.people.utils.common.Result;

/**
 * @author : FENGZHI
 * create at:  2020/2/23  下午10:51
 * @description: 维保业务层接口
 */
public interface IMaintenanceService {
    Result findAllMaintenance();

    Result addMaintenance(Maintenance maintenance);

    Result findMaintenanceByEquipmentId(String eid);
}
