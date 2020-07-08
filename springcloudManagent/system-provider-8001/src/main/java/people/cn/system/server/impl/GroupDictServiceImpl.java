package people.cn.system.server.impl;

import org.springframework.stereotype.Service;
import people.cn.bean.GroupDict;
import people.cn.system.dao.GroupDictMapper;
import people.cn.system.server.IGroupDictService;

import javax.annotation.Resource;

@Service
public class GroupDictServiceImpl implements IGroupDictService {
    @Resource
    private GroupDictMapper groupDictMapper;

    @Override
    public GroupDict findGroupById(String id) {
        return groupDictMapper.findGroupById(id);
    }
}
