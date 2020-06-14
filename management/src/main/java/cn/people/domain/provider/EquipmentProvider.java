package cn.people.domain.provider;

import org.springframework.stereotype.Component;

/**
 * @author : FENGZHI
 * create at:  2020/2/27  下午8:54
 * @description: 设备提供类
 */
public class EquipmentProvider {
    public String findEquipmentByCodeAndBrand(String brand,String code){

        StringBuffer sql = new StringBuffer("select * from equipment where brand=");
        if (brand != null){
            sql.append("'"+brand+"'");
        }
        if (code != null){
            sql.append(" "+"and id="+"'"+code+"'");
        }
        System.out.println(sql);

        return sql.toString();
    }
}
