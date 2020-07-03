package people.cn.common.factory;

import people.cn.common.ExcelUtils;

/**
 * 文件工厂
 */
public class FileFactory {
    public static ExcelUtils getExcel(){
        return new ExcelUtils();
    }

}
