package cn.people.utils.common.pdfUtil;

import cn.people.domain.UserInfo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/10  下午11:48
 * @description: user pdf creat pdf
 */
@Slf4j
@Component
public class UserPdfUtil extends PdfUtilAbstract {


    @Override
    public PdfPTable creatTable(PdfWriter writer, List<?> list) throws IOException, DocumentException {
        PdfPCell cell;
        PdfPTable table = new PdfPTable(5);
        String[] strings = {"用户名", "创建日期", "部门", "用户状态", "姓名"};
        for (String name :strings) {
            table.addCell(setCell(name));
        }
        List<UserInfo> userInfos = (List<UserInfo>) list;

        for (UserInfo userInfo :userInfos) {
            table.addCell(setCell(userInfo.getUsername() ));
            table.addCell(setCell(userInfo.getCreateDate() ));
            table.addCell(setCell(userInfo.getGroup().getGroupName()));
            table.addCell(setCell(userInfo.getStatusString() ));
            table.addCell(setCell(userInfo.getName() ));
        }
        return table;
    }

    @Override
    public Paragraph title() throws IOException, DocumentException {
        return new Paragraph("用户信息",this.fontChinese());
    }

    @Override
    public Boolean checkFileName(String fileName) {
        String substring = fileName.substring(fileName.lastIndexOf("."));
        if (".pdf".equals(substring)){
            return true;
        }
        return false;
    }

    @Override
    public void preview(String filePath, HttpServletResponse response) throws IOException {

        byte[] bytes = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","fileName="+new String(filePath.getBytes(),"iso8859-1"));
        ServletOutputStream os = response.getOutputStream();
        try {
            fis = new FileInputStream(new File(filePath));
            bis = new BufferedInputStream(fis);
            int i = bis.read(bytes);
            while (i != -1){
                os.write(bytes,0,i);
                os.flush();
                i = bis.read(bytes);
            }
        }catch (Exception e){
            log.error(e.toString());
        }finally {
            fis.close();
            bis.close();
            os.close();
        }
    }
}
