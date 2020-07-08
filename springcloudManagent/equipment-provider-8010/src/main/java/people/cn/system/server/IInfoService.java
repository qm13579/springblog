package people.cn.system.server;


import com.github.pagehelper.PageInfo;
import com.itextpdf.text.DocumentException;
import people.cn.bean.EquipmentInfo;
import people.cn.bean.LogDot;
import people.cn.bean.LogPojo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/5  下午7:54
 * @description: info
 */
public interface IInfoService {
    /**
     *
     * @param log
     */
    void logInsert(LogPojo log);

//    PageInfo findLog(LogDot logDot);

    void getInfoPdf(HttpServletResponse response) throws IOException, DocumentException;

    /**
     * 增加设备信息
     * @param info
     */
    void insertEquipmentInfo(EquipmentInfo info);

    /**
     * 查找设备信息
     * @param equipmentId
     */
    void findEquipmentInfo(String equipmentId);

    List<EquipmentInfo> getEquipmentInfo(String equipmentId);

    List<String> getWeekPVDate();

    PageInfo findLog(LogDot logDot);
}
