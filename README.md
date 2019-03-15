### Enum-Util
=
* 项目描述：
    > 将Enum中的code和value进行转换的工具类
* 使用场景：
    > 需要将Enum中code和value进行灵活互换的时候
* 场景描述：
    > 在平时的web开发中，http接口中的json数据中的某些字段，总伴随着一些状态，
    例如：dealing、dealt等状态字段，而数据库中存储的也许是1、2、3等数字，
    该工具类就是用来将其相互转换、统一管理的，无需每次手写大量冗余代码。
* 使用方式：
    > 所有的Enum类都需要有一个 int|Integer 类型的code属性和一个 String 类型的value属性，
    代码使用方式如下：
    
    * 根据code获取value
    
    ```
    String value = EnumUtil.getValueByCode(TestEnum.class, 1);
    ```
    
    * 根据value获取code
    ```
    int code = EnumUtil.getCodeByValue(TestEnum.class, "dealing");
    ```
* 注意：
    > 此版本写死了code和value，虽然对原有代码的入侵性不强，但限制了code和value
    的命名，下版本改善此缺陷。