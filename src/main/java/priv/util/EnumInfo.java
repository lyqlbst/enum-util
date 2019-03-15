package priv.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author yuqiang lin
 * @description EnumInfo，存储两个map，code和value对应、value和code对应
 * @email 1098387108@qq.com
 * @date 2019/3/15 3:15 PM
 */
@Data
@AllArgsConstructor
class EnumInfo {
    /**
     * code-value
     */
    private Map<Integer, String> codeValueMap;
    /**
     * value-code
     */
    private Map<String, Integer> valueCodeMap;
}