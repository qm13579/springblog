import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import people.cn.EquipmentProvider8010;
import people.cn.bean.Equipment;
import people.cn.system.server.IEquipmentService;
import people.cn.system.server.impl.UserServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EquipmentProvider8010.class)
public class EquipemntTest {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test1(){
        List<Equipment> allEquipment = equipmentService.findAllEquipment();
        System.out.println(allEquipment);
    }

    @Test
    public void test2(){
        userService.findUserByUsername("admin");
    }

    @Test
    public void test3(){
        String s1 = new String("hello");
        System.out.println(s1.hashCode());
        String s2 = "hello";
        System.out.println(s2.hashCode());
        new StringBuffer("a");
    }
}
