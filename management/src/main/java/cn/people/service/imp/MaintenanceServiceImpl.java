package cn.people.service.imp;

import cn.people.domain.Equipment;
import cn.people.domain.Maintenance;
import cn.people.mapper.EquipmentMapper;
import cn.people.mapper.MaintenanceMapper;
import cn.people.service.IMaintenanceService;
import cn.people.utils.IdWorker;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import cn.people.utils.observer.ObserverInfo;
import cn.people.utils.observer.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 * @author : FENGZHI
 * create at:  2020/2/23  下午10:58
 * @description: 维保业务层实现类
 */
@Service
public class MaintenanceServiceImpl  implements IMaintenanceService, Subject {
    private ArrayList<ObserverInfo> observers;
    private SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    private Result result = null;
    @Resource
    private EquipmentServiceImpl equipmentService;
    @Resource
    private MaintenanceMapper maintenanceMapper;
    @Autowired
    private IdWorker idWorker;
    @Override
    public Result findAllMaintenance() {
        List<Maintenance> allMaintenance = maintenanceMapper.findAllMaintenance();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(allMaintenance);
        return result;
    }

    @Override
    public Result addMaintenance(Maintenance maintenance) {
        maintenance.setId(idWorker.nextId()+"");
        maintenance.setDate(DATE.format(new Date()));
        if (maintenance.getStatus() == 1 & maintenance.getDescribe() != null){
            maintenanceMapper.insertMaintenance(maintenance);
            result = new  Result(ResultCode.SUCCESS);
        } else if (maintenance.getStatus() == 0){
            maintenanceMapper.insertMaintenance(maintenance);
            result = new  Result(ResultCode.SUCCESS);
        } else if (maintenance.getStatus() == null){
            result = new  Result(ResultCode.FAIL);
            result.setMessage("请填写设备状态");
        }else {
            //当维保故障时设备状态改为故障
            notifyObserver(maintenance.getEquipment().getId());
            addObserver(equipmentService);
            result = new Result(ResultCode.SUCCESS);
        }

        return result;
    }

    @Override
    public Result findMaintenanceByEquipmentId(String eid) {
        List<Maintenance> maintenances= maintenanceMapper.findAllMaintenanceByEquipment(eid);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(maintenances);
        return result;
    }

    @Override
    public void addObserver(ObserverInfo observer) {
        observers.add(observer);
    }

    @Override
    public void deleObserver(ObserverInfo observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String info) {
        for (ObserverInfo observer :observers) {
            observer.update(info);
        }
    }
}
