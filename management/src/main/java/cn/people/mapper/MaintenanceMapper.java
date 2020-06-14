package cn.people.mapper;

import cn.people.domain.Equipment;
import cn.people.domain.Maintenance;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/22  上午10:33
 * @description: 维保持久层
 */
public interface MaintenanceMapper {
    /**
     *
     * @return
     */
    @Select("select * from maintenance")
    @Results({
            @Result(property = "describe" ,column = "desc_info"),
            @Result(property = "equipment",column = "equipment",javaType = Equipment.class,one = @One(select = "cn.people.mapper.EquipmentMapper.findEquipmentById"))
    })
    public List<Maintenance> findAllMaintenance();

    /**
     *
     * @param eid
     * @return
     */
    @Select("select * from maintenance where equipment = #{eid}")
    @Results({
            @Result(property = "describe" ,column = "desc_info"),
            @Result(property = "equipment",column = "equipment",javaType = Equipment.class,one = @One(select = "cn.people.mapper.EquipmentMapper.findEquipmentById"))
    })
    public List<Maintenance> findAllMaintenanceByEquipment(String eid);

    /**
     * 插入维保记录
     * @param maintenance
     */
    @Insert("insert into maintenance(id,name,mobile,date,equipment,status,desc_info) " +
            "values(#{id},#{name},#{mobile},#{date},#{equipment.id},#{status},#{describe})")
    public void insertMaintenance(Maintenance maintenance);

    /**
     * 工单状态由故障更新为正常
     * @param equipmentId
     */
    @Update("update maintenance set status=0 where equipment=#{equipmentId}")
    void changeStatus(String equipmentId);
}
