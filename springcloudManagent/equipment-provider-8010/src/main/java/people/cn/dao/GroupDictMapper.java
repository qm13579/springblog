package people.cn.dao;

import people.cn.bean.GroupDict;

/**
 * @author : FENGZHI
 * create at:  2020/7/7  下午9:52
 * @description:
 */
public interface GroupDictMapper {
    /**
     * id
     * @param id
     * @return
     */
    GroupDict selectGroupDictById(String id);
}
