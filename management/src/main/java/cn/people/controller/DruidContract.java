package cn.people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : FENGZHI
 * create at:  2020/3/25  下午10:42
 * @description:
 */
@RestController
public class DruidContract {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String,Object>map(){

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * from department");
        return maps.get(0);
    }
}
