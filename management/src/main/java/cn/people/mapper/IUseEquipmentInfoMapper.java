package cn.people.mapper;

import cn.people.domain.Equipment;
import cn.people.domain.UseEquipmentInfo;
import cn.people.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import javax.print.DocFlavor;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/21  上午10:44
 * @description: 用户设备使用信息
 */
public interface IUseEquipmentInfoMapper {

    /**
     * 查询使用信息及当前使用设备
     * @return
     */
    @Select("select * from use_equipment_info where status=0")
    @Results({
            @Result(property = "equipment",column = "equipment_id",javaType = Equipment.class,one = @One(select = "cn.people.mapper.EquipmentMapper.findEquipmentById")),
            @Result(property = "user",column = "user_id" ,javaType = UserInfo.class,one = @One(select = "cn.people.mapper.IUserMapper.findUserById"))
    })
    List<UseEquipmentInfo> findAllUseEquipment();

    /**
     * 根据用户id查询用户所有使用设备记录
     * @param uid
     * @return
     */
    @Select("select * from use_equipment_info where user_id=#{uid}")
    List<UseEquipmentInfo> findAllUseEquipmentByUserId(String uid);

    /**
     * 添加工单id为自增
     * @param info
     */
    @Insert("insert into use_equipment_info(user_id,equipment_id,date,status) " +
            "values(#{user.id},#{equipment.id},#{date},#{status})")
    public void addUseEquipment(UseEquipmentInfo info);

    /**
     * 更新使用设备单
     * @param info
     */
    @Update("update use_equipment_info set user_id=#{user.id},equipment_id=#{equipment.id},date=#{date},status=#{status} where id =#{id}")
    public void updateUseEquipment(UseEquipmentInfo info);

    /**
     * 查找历史工单
     * @return
     */
    @Select("select * from use_equipment_info where status=1")
    @Results({
            @Result(property = "equipment",column = "equipment_id",javaType = Equipment.class,one = @One(select = "cn.people.mapper.EquipmentMapper.findEquipmentById")),
            @Result(property = "user",column = "user_id" ,javaType = UserInfo.class,one = @One(select = "cn.people.mapper.IUserMapper.findUserById"))
    })
    List<UseEquipmentInfo> findHistoryUseEquipment();

    /**
     * 通过设备id查询工单在用
     * @param eid
     * @return
     */
    @Select("select * from use_equipment_info where equipment_id=#{eid} and status=0 ")
    @Results({
            @Result(property = "equipment",column = "equipment_id",javaType = Equipment.class,one = @One(select = "cn.people.mapper.EquipmentMapper.findEquipmentById")),
            @Result(property = "user",column = "user_id",javaType = UserInfo.class,one = @One(select = "cn.people.mapper.IUserMapper.findUserById"))
    })
    UseEquipmentInfo findUseEquipmentByEquipmentByid(String eid);

    /**
     * 停用工单
     * @param id
     * @param endDate
     */
    @Update("update use_equipment_info set status=1,end_date=#{endDate} where id = #{id}")
    void stopUseEquipmentById(@Param("id") int id,@Param("endDate") String endDate);

    /**
     * 设备停用，删除使用人
     * @param eid
     */
    @Delete("delete from use_equipment_info where equipment_id=#{eid}")
    void stopUseEquipmentByEId(String eid);

    /**
     * 根据用户查找使用信息
     * @param eid
     * @return
     */
    @Select("SELECT * FROM use_equipment_info WHERE equipment_id=#{eid} AND status=0")
    @Results({
            @Result(property = "user",column = "user_id",javaType = UserInfo.class,one = @One(select = "cn.people.mapper.IUserMapper.findUserById"))
    })
    UseEquipmentInfo findUseEquipmentByEId(String eid);
}
