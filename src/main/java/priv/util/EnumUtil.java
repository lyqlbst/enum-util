package priv.util;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuqiang lin
 * @description 转换code和value
 * @email 1098387108@qq.com
 * @date 2019/3/15 2:08 PM
 */
public class EnumUtil {
    private EnumUtil() {
    }

    /**
     * key: Enum类的typeName
     * value: code和value的对应信息
     */
    private static final Map<String, EnumInfo> ENUM_INFO_MAP = new ConcurrentHashMap<>();

    /**
     * 获取枚举类中和code对应的value值
     *
     * @param enumClass 枚举类所属的类
     * @param code      code值
     * @param <E>       Enum类
     * @return 对应的value值，若不存在，则抛出异常
     */
    public static <E extends Enum> String getValueByCode(Class<E> enumClass, Integer code) throws EnumConstantNotExistsException {
        String enumInfoKey = getEnumInfoKey(enumClass);
        if (ENUM_INFO_MAP.containsKey(enumInfoKey)) {
            return getValueByCode(enumClass.getTypeName(), code);
        }
        buildEnumInfo(enumClass);
        return getValueByCode(enumInfoKey, code);
    }

    /**
     * 获取枚举类中和value对应的code值
     *
     * @param enumClass 枚举类所属的类
     * @param value     value值
     * @param <E>       Enum类
     * @return 对应的value值，若不存在，则抛出异常
     */
    public static <E extends Enum> Integer getCodeByValue(Class<E> enumClass, String value) throws EnumConstantNotExistsException {
        String enumInfoKey = getEnumInfoKey(enumClass);
        if (ENUM_INFO_MAP.containsKey(enumInfoKey)) {
            return getCodeByValue(enumClass.getTypeName(), value);
        }
        buildEnumInfo(enumClass);
        return getCodeByValue(enumInfoKey, value);
    }

    /**
     * 获取ENUM_INFO的key
     *
     * @param enumClass 枚举类
     * @return 枚举类名
     */
    private static String getEnumInfoKey(Class<? extends Enum> enumClass) {
        return enumClass.getTypeName();
    }

    /**
     * 在缓存中根据code查value
     *
     * @param typeName Enum类名
     * @param code     code值
     * @return value值
     */
    private static String getValueByCode(String typeName, Integer code) throws EnumConstantNotExistsException {
        String value = ENUM_INFO_MAP.get(typeName).getCodeValueMap().get(code);
        return Optional.ofNullable(value).orElseThrow(() -> new EnumConstantNotExistsException("code " + code + " does not exists in " + typeName + ".class."));
    }

    /**
     * 在缓存中根据value查code
     *
     * @param typeName Enum类名
     * @param value    value值
     * @return code值
     */
    private static Integer getCodeByValue(String typeName, String value) throws EnumConstantNotExistsException {
        Integer code = ENUM_INFO_MAP.get(typeName).getValueCodeMap().get(value);
        return Optional.ofNullable(code).orElseThrow(() -> new EnumConstantNotExistsException("value " + value + " does not exists in " + typeName + ".calss."));
    }

    /**
     * 构建ENUM_INFO缓存
     *
     * @param enumClass 枚举类
     */
    private static void buildEnumInfo(Class<? extends Enum> enumClass) {
        checkEnumMembers(enumClass);
        int enumSize = enumClass.getEnumConstants().length;
        EnumInfo enumInfo = new EnumInfo(new HashMap<>(enumSize, 1), new HashMap<>(enumSize, 1));
        for (Enum enumConstant : enumClass.getEnumConstants()) {
            Integer code = getFieldValue(enumConstant, getField(enumClass, "code"));
            String value = getFieldValue(enumConstant, getField(enumClass, "value"));
            enumInfo.getCodeValueMap().put(code, value);
            enumInfo.getValueCodeMap().put(value, code);
        }
        // 不存在时再put
        ENUM_INFO_MAP.putIfAbsent(getEnumInfoKey(enumClass), enumInfo);
    }

    /**
     * 通过反射获取属性的值
     *
     * @param enumConstant Enum实例
     * @param field        字段信息
     * @param <T>          属性的类
     * @return 属性值
     */
    @SuppressWarnings("Unchecked")
    private static <T> T getFieldValue(Enum enumConstant, Field field) {
        field.setAccessible(true);
        try {
            return (T) field.get(enumConstant);
        } catch (IllegalAccessException e) {
            throw new EnumTransferException(e);
        }
    }

    /**
     * 判断是否存在code和value，还有其类型
     *
     * @param enumClass 枚举类所属class
     */
    private static void checkEnumMembers(Class<? extends Enum> enumClass) {
        checkFieldType(getField(enumClass, "code").getType().getTypeName(), "int", Integer.class.getTypeName());
        checkFieldType(getField(enumClass, "value").getType().getTypeName(), String.class.getTypeName());
    }

    /**
     * 判断field所属的className是否在给定的className集合中
     *
     * @param fieldTypeName 字段所属类名
     * @param typeNames     className集合
     */
    private static void checkFieldType(String fieldTypeName, String... typeNames) {
        if (Objects.isNull(typeNames) || typeNames.length == 0) {
            return;
        }
        for (String typeName : typeNames) {
            if (typeName.equals(fieldTypeName)) {
                return;
            }
        }
        StringBuilder messageSB = new StringBuilder("The code attribute must be defined as");
        for (String typeName : typeNames) {
            messageSB.append(" ").append(typeName).append(" or");
        }
        messageSB.setLength(messageSB.length() - 3);
        messageSB.append(".");
        throw new EnumTransferException(messageSB.toString());
    }

    /**
     * 获取Enum类的属性
     *
     * @param enumClass Enum类的Class
     * @param fieldName 字段名
     * @return field实例
     */
    private static Field getField(Class<? extends Enum> enumClass, String fieldName) {
        try {
            return enumClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new EnumTransferException("The enumeration class you defined has no " + fieldName + " attribute.");
        }
    }
}
