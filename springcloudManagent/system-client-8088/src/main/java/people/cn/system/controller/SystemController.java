package people.cn.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import people.cn.common.Result;

@RequestMapping("equipment")
@RestController
public class SystemController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://EQUIPMENT-PROVIDER";

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result findEquipmentAll(){
        return restTemplate.getForObject(URL+"/equipment/",Result.class);
    }

}
