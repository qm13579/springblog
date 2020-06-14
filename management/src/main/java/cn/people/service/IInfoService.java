package cn.people.service;

import cn.people.domain.EquipmentInfo;
import cn.people.domain.LogPojo;
import cn.people.dot.LogDot;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    PageInfo findLog(LogDot logDot);

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
}
