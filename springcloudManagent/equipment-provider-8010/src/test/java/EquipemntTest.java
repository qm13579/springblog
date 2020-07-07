import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import people.cn.EquipmentProvider8010;
import people.cn.controller.EquipmentController;
import people.cn.dao.IUseEquipmentInfoMapper;
import people.cn.server.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EquipmentProvider8010.class)
public class EquipemntTest {

    @Autowired
    private EquipmentController equipmentController;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test1(){
        equipmentController.findAllEquipment();
    }

    @Test
    public void test2(){
        userService.findUserByUsername("admin");
    }
}
