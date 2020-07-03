package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/3/16  下午3:05
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractEquipment implements Serializable {

    private String cid;

    private String contractName;

    private String startTime;

    private Integer status;

    private String partyA;

    private String partyALegal;

    private String partyALinkman;

    private String partyALinkmanMobile;

    private String partyB;

    private String partyBLegal;

    private String partyBLinkman;

    private String partyBLinkmanMobile;

    private String money;
    private String file;

    @Override
    public String toString() {
        return "ContractEquipment{" +
                "cid='" + cid + '\'' +
                ", contractName='" + contractName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", status=" + status +
                ", partyA='" + partyA + '\'' +
                ", partyALegal='" + partyALegal + '\'' +
                ", partyALinkman='" + partyALinkman + '\'' +
                ", partyALinkmanMobile='" + partyALinkmanMobile + '\'' +
                ", partyB='" + partyB + '\'' +
                ", partyBLegal='" + partyBLegal + '\'' +
                ", partyBLinkman='" + partyBLinkman + '\'' +
                ", partyBLinkmanMobile='" + partyBLinkmanMobile + '\'' +
                ", money='" + money + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
