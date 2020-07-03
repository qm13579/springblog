package people.cn.common.factory;


import people.cn.common.factory.base.PdfFactory;
import people.cn.common.pdfUtil.UserPdfUtil;

/**
 * @author : FENGZHI
 * create at:  2020/3/11  上午12:47
 * @description: pdf util
 */
public class PdfUtilFactory implements PdfFactory {
    @Override
    public UserPdfUtil getUserPdfUtil() {
        return new UserPdfUtil();
    }
}
