package people.cn.dao;


import people.cn.bean.GroupDict;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午3:43
 * @description: 分类查询
 */
public interface GroupDictMapper {

    public GroupDict findGroupById(String id);

}
