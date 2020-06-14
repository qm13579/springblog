package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : FENGZHI
 * create at:  2020/3/11  下午7:31
 * @description: 设备信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentInfo {

    private String id;
    private String date;
    private String name;
    private String operation;
    private Equipment equipment;
    private String operationDesc;

    public EquipmentInfo(String id, String date, String name, String operation, Equipment equipment) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.operation = operation;
        this.equipment = equipment;
    }

    public String getOperationDesc() {
        if (operation.equals("add")){
            operationDesc = "新增了设备";
        }
        if (operation.equals("stop")){
            operationDesc = "停用了这台设备";
        }
        if (operation.equals("start")){
            operationDesc = "启用了这台设备";
        }

        if (operation.equals("0")){
            operationDesc = "设备启用";
        }
        if (operation.equals("1")){
            operationDesc = "设备维修中";
        }
        if (operation.equals("2")){
            operationDesc = "设备未启用";
        }
        if (operation.equals("4")){
            operationDesc = "设备停用";
        }
        return operationDesc;
    }

    @Override
    public String toString() {
        return "EquipmentInfo{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", operation='" + operation + '\'' +
                ", equipment=" + equipment +
                '}';
    }
}
