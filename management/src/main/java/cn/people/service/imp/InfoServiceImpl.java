package cn.people.service.imp;

import cn.people.domain.EquipmentInfo;
import cn.people.domain.LogPojo;
import cn.people.domain.UserInfo;
import cn.people.dot.LogDot;
import cn.people.mapper.IUserMapper;
import cn.people.mapper.InfoMapper;
import cn.people.service.IInfoService;
import cn.people.utils.common.pdfUtil.UserPdfUtil;
import cn.people.utils.factory.PdfUtilFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



/**
 * @author : FENGZHI
 * create at:  2020/3/5  下午7:58
 * @description: 信息实现了
 */
@Service
public class InfoServiceImpl implements IInfoService {

    @Resource
    private InfoMapper infoMapper;
    @Resource
    private IUserMapper userMapper;
    @Autowired
    private UserPdfUtil userPdfUtil;
    @Override
    public void logInsert(LogPojo log) {
        infoMapper.logInsert(log);
    }


    @Override
    public PageInfo findLog(LogDot logDot) {
        PageHelper.startPage(logDot.getPageNum(),logDot.getPageSize());

        List<LogPojo> log = infoMapper.findLog();
        PageInfo<LogPojo> pageInfo = new PageInfo<>(log);

        return pageInfo;
    }

    @Override
    public void getInfoPdf(HttpServletResponse response) throws IOException, DocumentException {
        List<UserInfo> allUser = userMapper.findAllUser();
        PdfUtilFactory pdfUtilFactory = new PdfUtilFactory();
        pdfUtilFactory.getUserPdfUtil().setHeader(response,allUser);

    }

    @Override
    public void insertEquipmentInfo(EquipmentInfo info) {
        infoMapper.insertEquipmentInfo(info);
    }

    @Override
    public void findEquipmentInfo(String equipmentId) {
        infoMapper.findEquipmentInfo(equipmentId);
    }

    @Override
    public List<EquipmentInfo> getEquipmentInfo(String equipmentId) {
        return infoMapper.getEquipmentInfo(equipmentId);
    }

    @Override
    public List<String> getWeekPVDate() {
        return infoMapper.getWeekNumber();
    }

}
