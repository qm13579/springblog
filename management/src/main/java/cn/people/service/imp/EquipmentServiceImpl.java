package cn.people.service.imp;

import cn.people.domain.*;
import cn.people.mapper.EquipmentMapper;
import cn.people.mapper.IUseEquipmentInfoMapper;
import cn.people.mapper.MaintenanceMapper;
import cn.people.service.IEquipmentService;
import cn.people.service.IInfoService;
import cn.people.utils.IdWorker;
import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;
import cn.people.utils.observer.ObserverInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午2:06
 * @description: 设备信息
 */
@Slf4j
@Service
public class EquipmentServiceImpl implements IEquipmentService, ObserverInfo {
    @Resource
    private MaintenanceMapper maintenanceMapper;
    @Resource
    private EquipmentMapper equipmentMapper;
    @Autowired
    private IdWorker idWorker;
    @Resource
    private IInfoService infoService;
    @Resource
    private IUseEquipmentInfoMapper useEquipmentInfoMapper;

    SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Equipment> findAllEquipment() {
        return equipmentMapper.findAllEquipment();
    }

    @Override
    public List<Equipment> findEquipmentByGroupId(String gid) {

        return equipmentMapper.findEquipmentByGroupId(gid);
    }

    @Override
    public boolean updateEquipment(Equipment equipment) {
        Equipment equipmentById = equipmentMapper.findEquipmentById(equipment.getId());
        System.out.println("equip"+equipment.getStatus()+":"+equipment.getId());
        System.out.println("AAA"+equipmentById.getStatus());

        //判断现设备是否故障已修复，如时修复故障需更改工单状态
        if (equipmentById.getStatus()== 4 && equipment.getStatus() != 4){
            maintenanceMapper.changeStatus(equipment.getId());
        }
        if (!equipmentById.getStatus().equals(equipment.getStatus())){
            log.info("设备状态改变，记录信息");
            if (equipment.getStatus()== 4 ){
                //当设备变更为停用时，应从使用人手中回收设备
                if (equipment.getMetrics() != 1){
                    UseEquipmentInfo info = useEquipmentInfoMapper.findUseEquipmentByEquipmentByid(equipment.getId());
                    useEquipmentInfoMapper.stopUseEquipmentById(info.getId(),DATE.format(new Date()));
                    equipment.setMetrics(1);
                }
                System.out.println("==============");
                infoService.insertEquipmentInfo(
                        new EquipmentInfo(
                                idWorker.nextId()+"",DATE.format(new Date()),"user",equipment.getStatus()+"",equipment
                        )
                );
            }else {
                infoService.insertEquipmentInfo(new EquipmentInfo(
                        idWorker.nextId()+"",DATE.format(new Date()),"user",equipment.getStatus()+"",equipment));
            }
        }
        equipmentMapper.updateEquipment(equipment);

        return true;
    }

    @Override
    public void saveEquipment(Equipment equipment) {

        equipment.setId(idWorker.nextId()+"");
        equipment.setStatus(2);
        equipment.setMetrics(1);
        equipment.setRegisterDate(DATE.format(new Date()));

        equipmentMapper.saveEquipment(equipment);
        infoService.insertEquipmentInfo(
                new EquipmentInfo(idWorker.nextId()+"",DATE.format(new Date()), "user","add",equipment));
    }

    @Override
    public void saveUseEquipment(UseEquipmentInfo info) {
        info.setDate(DATE.format(new Date()));
        equipmentMapper.saveUseEquipment(info);
        infoService.insertEquipmentInfo(
                new EquipmentInfo(idWorker.nextId()+"",DATE.format(new Date()),info.getUser().getName(),"update",info.getEquipment())
        );
    }

    /***
     * 停用设备时同时设备使用人
     * @param ueid
     */
    @Override
    public void stopUseEquipmentById(String ueid) {
        Equipment equipment = new Equipment();
        equipment.setId(ueid);
        infoService.insertEquipmentInfo(
                new EquipmentInfo(idWorker.nextId()+"",DATE.format(new Date()),"admin" ,"stop", equipment)
        );
        equipmentMapper.stopUseEquipmentById(ueid);
    }

    @Override
    public void updateUseEquipment(UseEquipmentInfo info) {
        equipmentMapper.updateUseEquipment(info);
    }

    @Override
    public List<UseEquipmentInfo> findAllUseEquipment() {

        return equipmentMapper.findAllUseEquipment();
    }

    @Override
    public List<UseEquipmentInfo> findStatusUseEquipment(String status) {

        return equipmentMapper.findStatusUseEquipment(status);
    }

    @Override
    public Result findEquipmentGroup() {
        List<GroupDict> group = equipmentMapper.findEquipmentGroup();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(group);
        return result;
    }

    @Override
    public void addGroupToEquipment(GroupDict group) {
        group.setId(idWorker.nextId()+"");
        equipmentMapper.addGroupToEquipment(group);
    }

    @Override
    public void updateGroupToEquipment(GroupDict group) {
        equipmentMapper.updateGroupToEquipment(group);
    }

    @Override
    public void update(String equipmentId) {
        equipmentMapper.faultEquipment(equipmentId);
    }
}
