package people.cn.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import people.cn.common.BaseEntity;
import people.cn.common.annotation.Excel;

import java.io.Serializable;

/**
 * id、品牌、设备类型、型号、登记日期、生产商、销售商、运维电话
 * @author : FENGZHI
 * create at:  2020/2/14  下午4:30
 * @description: 设备实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment extends BaseEntity {
    public static final String KEY = "equipment";
    @Excel(name = "ID")
    private String id;

    @Excel(name = "品牌")
    private String brand;

    private GroupDict type;

    @Excel(name = "型号")
    private String part;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private String registerDate;
    @Excel(name = "生产商")
    private String producers;
    @Excel(name = "销售商")
    private String seller;
    @Excel(name = "联系电话")
    private String mobile;

    private String code;
    /**状态 0:已启用，1：待维修，2：未启用，4：已停用*/
    private Integer status;
    /**0:已分配 1:未分配*/
    private Integer metrics;
    /**合同信息*/
    private DraftContract contract;




    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", type=" + type +
                ", part='" + part + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", producers='" + producers + '\'' +
                ", seller='" + seller + '\'' +
                ", mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                ", metrics=" + metrics +
                '}';
    }
}