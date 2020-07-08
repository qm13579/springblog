package people.cn.system.server;

import people.cn.bean.GroupDict;

public interface IGroupDictService {
    GroupDict findGroupById(String id);
}
