package cn.people.mapper;

import cn.people.domain.EquipmentInfo;
import cn.people.domain.InfoPojo;
import cn.people.domain.LogPojo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/22  上午10:32
 * @description: 信息持久层
 */
public interface InfoMapper {

    /**
     * 查询所有信息
     * @return
     */
    @Select("select * from info_pojo")
    public List<InfoPojo> findAllInfo();

    /**
     * 查询未推送的信息
     * @return
     */
    @Select("select * from info_pojo where status=1")
    public List<InfoPojo> findAllStatus();

    /**
     * 插入一条新的信息
     * @param info
     */
    @Insert("insert into info_pojo(id,source_info,content,status,date) " +
            "values(#{id},#{source},#{content},#{status},#{date})")
    public void insertInfo(InfoPojo info);

    /**
     * 插入日志
     * @param log
     */
    @Insert("insert into log_info(id,url,time,user,date,method) values(#{id},#{url},#{time},#{user},#{date},#{method}) ")
    void logInsert(LogPojo log);

    /**
     * 查询日子记录
     * @return
     */
    @Select("select * from log_info")
    List<LogPojo> findLog();

    /**
     * 插入设备信息
     * @param info
     */
    @Insert("insert into info_pojo(id,name,equipment,operation,date) values(" +
            "#{id},#{name},#{equipment.id},#{operation},#{date})")
    void insertEquipmentInfo(EquipmentInfo info);

    /**
     * 通过设备id查找设备的信息
     * @param equipmentId
     */
    @Select("select * from info_pojo where equipment=#{equipmentId}")
    void findEquipmentInfo(String equipmentId);

    /**
     * 获取设备信息
     * @param equipmentId
     * @return
     */
    @Select("select * from info_pojo where equipment=#{equipmentId}")
    List<EquipmentInfo> getEquipmentInfo(String equipmentId);

    /**
     * 获取一周的数据统计
     * @return
     */
    @Select("select count(*) from log_info where date > date_sub(curdate(),interval 7 day) group by date")
    List<String> getWeekNumber();
}
