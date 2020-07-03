package cn.people.utils.factory;

import cn.people.domain.UserInfo;
import cn.people.utils.common.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件工厂
 */
public class FileFactory {
    public static ExcelUtils getExcel(){
        return new ExcelUtils();
    }

}
