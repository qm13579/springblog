package people.cn.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping
@RestController
public class SystemController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "SYSTEM-PROVIDER-SYSTEM";

}
