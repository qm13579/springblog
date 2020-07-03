package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/25  下午9:18
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DraftContract {
    private String id;
    private String context;
    private Integer status;
    private String title;
    /**合同制定人*/
    private UserInfo user;
    /**相关合同的关联设备*/
    private List<Equipment> equipments;


    @Override
    public String toString() {
        return "DraftContract{" +
                "id='" + id + '\'' +
                ", context='" + context + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", user=" + user +
                ", equipments=" + equipments +
                '}';
    }
}
