package cn.people.mapper;

import cn.people.domain.GroupDict;
import cn.people.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午3:43
 * @description: 分类查询
 */
public interface GroupDictMapper {
    @Select("select * from group_dict where id = #{id}")
    public GroupDict findGroupById(String id);

}
