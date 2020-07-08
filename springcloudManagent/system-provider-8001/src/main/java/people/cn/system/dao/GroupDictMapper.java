package people.cn.system.dao;


import org.apache.ibatis.annotations.Mapper;
import people.cn.bean.GroupDict;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午3:43
 * @description: 分类查询
 */
@Mapper
public interface GroupDictMapper {
    /**
     * id
     * @param id
     * @return
     */
    GroupDict findGroupById(String id);

}
