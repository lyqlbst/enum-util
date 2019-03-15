package priv.util;

/**
 * @author yuqiang lin
 * @description 当属性不存在时抛出的异常，需要正常捕获
 * @email 1098387108@qq.com
 * @date 2019/3/15 6:29 PM
 */
public class EnumConstantNotExistsException extends Exception {
    public EnumConstantNotExistsException(String msg) {
        super(msg);
    }
}
