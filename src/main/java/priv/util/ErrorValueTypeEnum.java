package priv.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuqiang lin
 * @description value不为String的Enum
 * @email 1098387108@qq.com
 * @date 2019/3/15 6:25 PM
 */
@Getter
@AllArgsConstructor
public enum ErrorValueTypeEnum {
    VALUE_TYPE_ERROR(1, 1);
    private int code;
    private int value;
}
