package priv.util;

/**
 * @author yuqiang lin
 * @description 枚举类转换错误异常类
 * @email 1098387108@qq.com
 * @date 2019/3/15 3:15 PM
 */
class EnumTransferException extends RuntimeException {
    EnumTransferException(String msg) {
        super(msg);
    }

    EnumTransferException(Exception e) {
        super(e);
    }
}
