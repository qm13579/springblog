package people.cn.server.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import people.cn.bean.EquipmentInfo;
import people.cn.bean.UseEquipmentInfo;
import people.cn.bean.UserInfo;
import people.cn.common.IdWorker;
import people.cn.dao.EquipmentMapper;
import people.cn.dao.IUseEquipmentInfoMapper;
import people.cn.dao.UserMapper;
import people.cn.server.IInfoService;
import people.cn.server.IUseEquipmentInfoService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/21  下午2:07
 * @description: 使用设备业务成
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UseEquipmentInfoServiceImpl implements IUseEquipmentInfoService {

    SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    @Resource
    private IUseEquipmentInfoMapper useEquipmentInfoMapper;
    @Resource
    private EquipmentMapper equipmentMapper;
    @Resource
    private IInfoService infoService;
    @Autowired
    private IdWorker idWorker;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<UseEquipmentInfo> findAllUseEquipment() {
        List<UseEquipmentInfo> allUseEquipment = useEquipmentInfoMapper.findAllUseEquipment();

        return useEquipmentInfoMapper.findAllUseEquipment();
    }

    /**
     * 在添加工单时，判断设备是否已被分配使用，如分配使用则先停止工单，再添加工单
     * @param info
     */
    @Override
    public void addUseEquipment(UseEquipmentInfo info) {
        UseEquipmentInfo equipmentInfo = useEquipmentInfoMapper.findUseEquipmentByEquipmentByid(info.getEquipment().getId());
        if (equipmentInfo != null){
            equipmentInfo.setStatus(1);
            useEquipmentInfoMapper.stopUseEquipmentById(equipmentInfo.getId(),DATE.format(new Date()));
        }
        info.setDate(DATE.format(new Date()));
        equipmentMapper.changeMetricsAndStatus(info.getEquipment().getId());
        info.setStatus(0);
        useEquipmentInfoMapper.addUseEquipment(info);
    }

    @Override
    public boolean updateUseEquipment(UseEquipmentInfo info) {
        UseEquipmentInfo useEquipment = useEquipmentInfoMapper.findUseEquipmentByEquipmentByid(info.getEquipment().getId());
        if (info.getStatus() != 0){
            return false;
        }
        if ( !info.getUser().getName().equals(useEquipment.getUser().getName())){
            //当用户改变更改时，存入设备信息
            infoService.insertEquipmentInfo(
                    new EquipmentInfo(idWorker.nextId()+"",DATE.format(new Date()),info.getUser().getName(),"update",info.getEquipment() )
            );
        }
        useEquipmentInfoMapper.updateUseEquipment(info);
        return true;
    }

    @Override
    public List<UseEquipmentInfo> findUseEquipmentByUserId(String uid) {
        return useEquipmentInfoMapper.findAllUseEquipmentByUserId(uid);
    }

    @Override
    public boolean metricsUseEquipment(UseEquipmentInfo info) {
        //先查后改
        UseEquipmentInfo useEquipmentInfo = useEquipmentInfoMapper.findUseEquipmentByEquipmentByid(info.getEquipment().getId());
        //如果设备状态未非正常使用则不能更改用户
        info.setDate(DATE.format(new Date()));
        info.setStatus(0);
        UserInfo user = userMapper.findUserById(info.getUser().getId());
        //新增工单
        if(useEquipmentInfo == null ){
            //改变设备状态
            equipmentMapper.changeMetricsAndStatus(info.getEquipment().getId());
            //设置工单状态，0:使用，1：废单
            useEquipmentInfoMapper.addUseEquipment(info);
            infoService.insertEquipmentInfo(
                    new EquipmentInfo(idWorker.nextId()+"",DATE.format(new Date()),user.getName(),"update",info.getEquipment()));

        }else {
            //用户改变
            if (!useEquipmentInfo.getUser().getId().equals(info.getUser().getId())){
                //停用工单-->新增工单
                useEquipmentInfoMapper.stopUseEquipmentById(useEquipmentInfo.getId(),DATE.format(new Date()));
                useEquipmentInfoMapper.addUseEquipment(info);
                infoService.insertEquipmentInfo(
                        new EquipmentInfo(idWorker.nextId()+"",DATE.format(new Date()),user.getName(),"update",info.getEquipment()));

            }
        }
        return true;
    }

    @Override
    public List<UseEquipmentInfo> findHistoryUseEquipment() {
        return useEquipmentInfoMapper.findHistoryUseEquipment();
    }

    @Override
    public UseEquipmentInfo findUseEquipmentByEId(String eid) {
        return useEquipmentInfoMapper.findUseEquipmentByEId(eid);
    }


}
