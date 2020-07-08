import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import people.cn.SystemProvider8001;
import people.cn.bean.GroupDict;
import people.cn.system.server.IGroupDictService;
import people.cn.system.server.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SystemProvider8001.class)
public class SsytemTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private IGroupDictService groupDictService;

    @Test
    public void test1(){
        userService.loadUserByUsername("admin");
    }
    @Test
    public void test2(){
        GroupDict group = groupDictService.findGroupById("6");
        System.out.println(group);
    }
}
