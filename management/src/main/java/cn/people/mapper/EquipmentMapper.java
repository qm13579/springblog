package cn.people.mapper;

import cn.people.domain.Equipment;
import cn.people.domain.GroupDict;
import cn.people.domain.UseEquipmentInfo;
import cn.people.domain.UserInfo;
import cn.people.domain.provider.EquipmentProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午2:32
 * @description: 设备持久层
 */
public interface EquipmentMapper {
    @Select("select * from equipment")
    @Results({
            @Result(property = "type",column = "groupDict",javaType = GroupDict.class,one = @One(select = "cn.people.mapper.GroupDictMapper.findGroupById"))
    })
    public List<Equipment> findAllEquipment();

    @Select("select * from equipment where id=#{id}")
    @Results({
            @Result(property = "type",column = "groupDict" ,javaType = GroupDict.class,one = @One(select = "cn.people.mapper.GroupDictMapper.findGroupById"))
    })
    public Equipment findEquipmentById(String id);

    /**
     * 通过设备类型查找
     * @param gid
     * @return
     */
    @Select("select * from equipment where groupDict = #{gid}")
    @Results({
            @Result(property = "type",column = "groupDict",javaType = GroupDict.class,one = @One(select = "cn.people.mapper.GroupDictMapper.findGroupById"))
    })
    public List<Equipment> findEquipmentByGroupId(String gid);

    @Update("update equipment set brand=#{brand},part=#{part},producers=#{producers},code=#{code}," +
            "seller=#{seller},mobile=#{mobile},groupDict=#{type.id},status=#{status}, metrics=#{metrics} where id=#{id}")
    public void updateEquipment(Equipment equipment);

    @Insert("insert into equipment(id,brand,part,registerDate,producers,seller,mobile,groupDict,status,metrics,code) " +
            "values(#{id},#{brand},#{part},#{registerDate},#{producers},#{seller},#{mobile},#{type.id},#{status},#{metrics},#{code})")
    public void saveEquipment(Equipment equipment);

    @Insert("insert into use_equipment_info(id,user_id,equipment_id,date,status) " +
            "values(#{id},#{user.id},#{equipment.id},#{date},#{status})")
    public void saveUseEquipment(UseEquipmentInfo info);

    @Update("update use_equipment_info set status=1 where id=#{ueid}")
    public void stopUseEquipmentById(String ueid);

    @Update("update use_equipment_info set user_id=#{user.id},equipment=#{equipment.id}")
    public void updateUseEquipment(UseEquipmentInfo info);

    @Select("select * from use_equipment_info")
    @Results({
            @Result(property = "user",column = "user_id",one = @One(select = "cn.people.mapper.IUserMapper.findUserById")),
            @Result(property = "equipment",column = "equipment_id",one = @One(select = "cn.people.mapper.EquipmentMapper.findEquipmentById"))
    })
    public List<UseEquipmentInfo> findAllUseEquipment();

    @Select("select * from use_equipment_info where status=#{status}")
    @Results({
            @Result(property = "user" ,column = "user_id",javaType = UserInfo.class,one = @One(select = "cn.people.mapper.IUserMapper.findUserById"))
    })
    public List<UseEquipmentInfo> findStatusUseEquipment(String status);

    /**
     * 分配设备后改变设备分配状态 0：正在使用
     *
     */
    @Update("update equipment set metrics=0 where id=#{eid}")
    public void changeMetrics(String eid);

    /**
     * 分配设备后启用设备并改变涉笔分配状态 metrics 0 :用户正在使用 status 0 ：设备已启用
     */
    @Update("update equipment set metrics=0,status=0 where id=#{eid}")
    void changeMetricsAndStatus(String eid);

    @Select("select * from group_dict where gid=2")
    List<GroupDict> findEquipmentGroup();

    /**
     * 新增设备分类
     * @param group
     */
    @Insert("insert into group_dict(id,groupName,gid) values(#{id},#{groupName},2)")
    void addGroupToEquipment(GroupDict group);

    /**
     * 更新设备分类
     * @param group
     */
    @Update("update group_dict set groupName=#{groupName} where id=#{id}")
    void updateGroupToEquipment(GroupDict group);

    /**
     * 通过模糊查找设备id
     */
    @Select("select * from equipment where code like concat ('%',#{code},'%') ")
    List<Equipment> likeEquepment(String code);

    /**
     *
     */
    @SelectProvider(type = EquipmentProvider.class,method = "findEquipmentByCodeAndBrand")
    List<Equipment> findEquipmentByCodeAndBrand(String brand,String code);

    /**
     * 设备状态故障
     * @param id
     * @return
     */
    @Update("update equipment set status=4 where id=#{id}")
    void faultEquipment(String id);

    /**
     * 当设备停用时，变更分配状态
     * @param id
     */
    @Update("update equipment set metrics=1 where id=#{id}")
    void stopMetrics(String id);
}
