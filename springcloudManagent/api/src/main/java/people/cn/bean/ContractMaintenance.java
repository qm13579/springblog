package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/3/18  下午1:03
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractMaintenance implements Serializable {

    private String cid;
    private String contractName;
    private String startTime;
    private String endTime;
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
        return "ContractMaintenance{" +
                "cid='" + cid + '\'' +
                ", contractName='" + contractName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
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
