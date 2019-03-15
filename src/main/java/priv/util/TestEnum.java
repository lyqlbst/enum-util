package priv.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuqiang lin
 * @description enum枚举、用于测试
 * @email 1098387108@qq.com
 * @date 2019/3/15 2:01 PM
 */
@Getter
@AllArgsConstructor
public enum TestEnum {
    A(1, "one"),
    B(2, "tow"),
    C(3, "three");
    /**
     * code
     */
    private int code;
    /**
     * value
     */
    private String value;
}
