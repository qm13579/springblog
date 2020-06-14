package cn.people.domain.provider;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/24  下午9:16
 * @description:
 */
public class UserProvider {

    public String insertRoleByUseId(List<String> list,String userId){
        StringBuffer sql = new StringBuffer("INSERT INTO user_role");
        sql.append("(user_id,role_id)");
        sql.append(" "+"values"+" ");
        for (String roleId:list) {
            roleId = "'"+roleId+"'";
            sql.append(MessageFormat.format("({0},{1});",userId,roleId));
        }
        return sql.toString();
    }
}
