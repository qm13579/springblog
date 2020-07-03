package people.cn.common.factory.base;

import people.cn.common.pdfUtil.UserPdfUtil;

/**
 * 简单工厂模式
 * @author : FENGZHI
 * create at:  2020/3/11  上午12:44
 * @description: pdf tactory
 */
public interface PdfFactory {
    /**
     * 生成用户pdf工具工厂
     * @return
     */
    UserPdfUtil getUserPdfUtil();
}
