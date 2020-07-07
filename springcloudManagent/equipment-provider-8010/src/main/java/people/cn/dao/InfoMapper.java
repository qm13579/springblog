package people.cn.dao;

import org.apache.ibatis.annotations.Mapper;
import people.cn.bean.EquipmentInfo;
import people.cn.bean.InfoPojo;
import people.cn.bean.LogPojo;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/22  上午10:32
 * @description: 信息持久层
 */
@Mapper
public interface InfoMapper {

    /**
     * 查询所有信息
     * @return
     */
    public List<InfoPojo> findAllInfo();

    /**
     * 查询未推送的信息
     * @return
     */
    public List<InfoPojo> findAllStatus();

    /**
     * 插入一条新的信息
     * @param info
     */
    public void insertInfo(InfoPojo info);

    /**
     * 插入日志
     * @param log
     */
    void logInsert(LogPojo log);

    /**
     * 查询日子记录
     * @return
     */
    List<LogPojo> findLog();

    /**
     * 插入设备信息
     * @param info
     */
    void insertEquipmentInfo(EquipmentInfo info);

    /**
     * 通过设备id查找设备的信息
     * @param equipmentId
     */
    void findEquipmentInfo(String equipmentId);

    /**
     * 获取设备信息
     * @param equipmentId
     * @return
     */
    List<EquipmentInfo> getEquipmentInfo(String equipmentId);

    /**
     * 获取一周的数据统计
     * @return
     */
    List<String> getWeekNumber();
}
