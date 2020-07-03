package people.cn.common.pdfUtil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/9  下午10:47
 * @description: pdf util
 */
abstract class PdfUtilAbstract {
    /**
     * 设置请求头
     * @param response
     * @throws IOException
     * @throws DocumentException
     */
    public void setHeader(HttpServletResponse response, List<?> list) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4.rotate());
        ServletOutputStream os = response.getOutputStream();
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, pdf);
        String fileName = System.currentTimeMillis()+".pdf";

        creatPdf(fileName,document,writer,list);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        response.setHeader("content-Disposition","filename="+new String(fileName.getBytes(),"iso8859-1"));
        pdf.writeTo(os);
        os.flush();
        os.close();
    }

    /**
     * 创建pdf
     * @param fileName
     * @param document
     * @throws IOException
     * @throws DocumentException
     */
    public void creatPdf(String fileName, Document document,PdfWriter writer,List<?> list) throws IOException, DocumentException {
//        PdfWriter.getInstance(document,new FileOutputStream(fileName));  //输出到本地
        document.addTitle("example of DPF");
        document.open();
        document.add(new Paragraph("电子设备管理系统",fontChinese()) );
        document.add(title());
        PdfPTable table = creatTable(writer,list);
        document.add(table);
        document.close();
    }
    public Paragraph title() throws IOException, DocumentException {

        return new Paragraph("Hello World",fontChinese());
    }
    /**
     * set font
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public Font fontChinese() throws IOException, DocumentException {

        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese);

        return font;
    }

    /**
     * cerat table
     *
     * @param writer
     * @return
     */
    public PdfPTable creatTable(PdfWriter writer, List<?> list) throws IOException, DocumentException {
        return null;
    }


    public   PdfPCell setCell(String value) throws IOException, DocumentException {

        return new PdfPCell(new Paragraph(value,fontChinese()));
    }

    /**
     * 检查文件是否为pdf
     * @param fileName
     * @return
     */
    public Boolean checkFileName(String fileName){
        return null;
    }

    /**
     * 文件预览
     * @param filePath
     * @param response
     */
    public void preview(String filePath,HttpServletResponse response) throws IOException {}
}
