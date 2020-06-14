package cn.people.utils.factory;

import cn.people.utils.common.pdfUtil.UserPdfUtil;
import cn.people.utils.factory.base.PdfFactory;

/**
 * @author : FENGZHI
 * create at:  2020/3/11  上午12:47
 * @description: pdf util
 */
public class PdfUtilFactory implements PdfFactory {
    @Override
    public  UserPdfUtil getUserPdfUtil() {
        return new UserPdfUtil();
    }
}
