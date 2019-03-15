package priv.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuqiang lin
 * @description code不为int或Integer的Enum
 * @email 1098387108@qq.com
 * @date 2019/3/15 6:20 PM
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeTypeEnum {
    ERROR_CODE_TYPE(1L, "");
    private Long code;
    private String value;
}
