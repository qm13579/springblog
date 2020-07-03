package people.cn.common;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import people.cn.bean.Equipment;
import people.cn.bean.GroupDict;
import people.cn.bean.UserInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : FENGZHI
 * create at:  2020/3/1  下午11:18
 * @description: 表格工具类
 */
@Component
public class ExcelUtil {
    @Autowired
    private IdWorker idWorker;

    /**
     * 设置请求头
     * @param workbook
     * @param res
     * @throws IOException
     */
    public void setHeard(Workbook workbook, HttpServletResponse res) throws IOException {
        long id = idWorker.nextId();
        ServletOutputStream os = res.getOutputStream();
        res.setContentType("application/x-download");
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Content-disposition", "attachment;filename=" +id+".xlsx");
        workbook.write(os);
        os.flush();
        os.close();
        //保存在本地
        FileOutputStream fileOutputStream = new FileOutputStream(id+".xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        workbook.close();
    }

    /**
     * 数据封装在excel中
     * @param data
     * @param tableName
     * @return
     * @throws IllegalAccessException
     */
    public  Workbook exportExcel(List<?> data ,String tableName) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //定义工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //定义工作表
        XSSFSheet sheet = workbook.createSheet(tableName);
        //定义行
        XSSFRow row = sheet.createRow(0);
        //定义初始行数
        int rowNum = 0;
        for (Object object:data) {
            row = sheet.createRow(rowNum);

            int columnNum=0;
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field:fields) {
                //设备表头
                if (rowNum == 0){
                    row.createCell(columnNum).setCellValue(field.getName());
                }else {
                    //通过反射获取值
                    field.setAccessible(true);
                    String type = field.getGenericType().toString();
                    if ("class java.lang.String".equals(type)){
                        String value = (String) field.get(object);
                        row.createCell(columnNum).setCellValue(value);
                    }else if ("class java.lang.Integer".equals(type)){
                        Integer value = (Integer) field.get(object);
                        row.createCell(columnNum).setCellValue(value);
                    }else if("class cn.people.domain.UserInfo".equals(type)){
                        UserInfo value = (UserInfo) field.get(object);
                        row.createCell(columnNum).setCellValue(value.getName());
                    }else if ("class cn.people.domain.Equipment".equals(type)){
                        Equipment value = (Equipment) field.get(object);
                        row.createCell(columnNum).setCellValue(value.getBrand());
                    }else if ("class cn.people.domain.GroupDict".equals(type)){
                        GroupDict value = (GroupDict) field.get(object);
                        row.createCell(columnNum).setCellValue(value.getGroupName());
                    }
                }
                //切换到下一个单元格
                columnNum++;
            }
            //切换到下一行
            rowNum++;
        }
        return workbook;
    }

    public List<Map> importExcel(MultipartFile file) throws IOException, InvalidFormatException {
        //创建工作薄
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        //只获取第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //{title:[title1,title2]},{data:[data1,data2]}
        List<Map> list = getRow(sheet);

        return list;
    }

    /**
     * 获取行数据
     * @param sheet
     * @return
     */
    private List<Map> getRow(Sheet sheet){
        int rowsNum = sheet.getPhysicalNumberOfRows();
        ArrayList<Map> arrayList = new ArrayList<>();
        ArrayList<Cell> titleList = new ArrayList<>();

        int rowNum=0;
        //获取每一行
        for(int i = 0; i<rowsNum; i++){
            if (i ==0 ){
                Row row = sheet.getRow(0);
                int cells = row.getPhysicalNumberOfCells();
                for (int j=0;j<cells;j++){
                    titleList.add(row.getCell(j));
                }
            }else {
                Map<Cell, Cell> map = new HashMap<>();
                Row row = sheet.getRow(i);
                int cells = row.getPhysicalNumberOfCells();
                for (int k=0;k<cells;k++){
                    Cell cellData = row.getCell(k);
                    Cell cellTitle = titleList.get(k);
                    map.put(cellTitle,cellData);
                }
                arrayList.add(map);
            }
            rowNum++;
        }
        return arrayList;
    }
}
