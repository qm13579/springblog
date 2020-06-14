package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * id、品牌、设备类型、型号、登记日期、生产商、销售商、运维电话
 * @author : FENGZHI
 * create at:  2020/2/14  下午4:30
 * @description: 设备实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment implements Serializable {
    private String id;
    private String brand;
    private GroupDict type;
    private String part;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private String registerDate;
    private String producers;
    private String seller;
    private String mobile;
    private String code;
    /**状态*/
    private Integer status;
    private String statusString;
    /**0:已分配 1:未分配*/
    private Integer metrics;
    private String  metricsString;
    /**合同信息*/
    private DraftContract contract;

    public String getStatusString() {
        if(this.getStatus() == 0){
            this.statusString = "已启用";
        }
        if (this.status == 1){
            this.statusString = "待维修";
        }
        if (this.status == 2){
            this.statusString = "未启用";
        }
        if (this.status==4){
            this.statusString="已停用";
        }
        return this.statusString;
    }

    public String getMetricsString() {

        if (metrics == 0){
            return metricsString="已分配使用";
        }
        else {
            return metricsString = "未分配使用";
        }
    }

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
                ", statusString='" + statusString + '\'' +
                ", metrics=" + metrics +
                ", metricsString='" + metricsString + '\'' +
                '}';
    }
}