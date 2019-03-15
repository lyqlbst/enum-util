package priv.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yuqiang lin
 * @description
 * @email 1098387108@qq.com
 * @date 2019/3/15 2:10 PM
 */
public class EnumUtilTest {
    @Before
    public void before() {
        System.out.println("开始测试...");
    }

    @Test
    public void testTransfer() throws EnumConstantNotExistsException {
        TestEnum enumConstant = TestEnum.A;

        String value = EnumUtil.getValueByCode(TestEnum.class, enumConstant.getCode());
        assertEquals(enumConstant.getValue(), value);
        System.out.println("transfer value: " + value);

        int code = EnumUtil.getCodeByValue(TestEnum.class, enumConstant.getValue());
        assertEquals(enumConstant.getCode(), code);
        System.out.println("transfer code: " + code);
    }

    @Test(expected = EnumConstantNotExistsException.class)
    public void testCodeNotExists() throws EnumConstantNotExistsException {
        try {
            EnumUtil.getValueByCode(TestEnum.class, -1);
        } catch (EnumConstantNotExistsException e) {
            System.out.println("code does not exists exception: " + e);
            throw e;
        }
    }

    @Test(expected = EnumConstantNotExistsException.class)
    public void testValueNotExists() throws EnumConstantNotExistsException {
        try {
            EnumUtil.getCodeByValue(TestEnum.class, "EMPTY");
        } catch (EnumConstantNotExistsException e) {
            System.out.println("value does not exists exception: " + e);
            throw e;
        }
    }

    @Test(expected = EnumTransferException.class)
    public void testEmpty() throws EnumConstantNotExistsException {
        try {
            EnumUtil.getCodeByValue(EmptyEnum.class, "");
        } catch (EnumTransferException e) {
            System.out.println("empty enum exception: " + e);
            throw e;
        }
    }

    @Test(expected = EnumTransferException.class)
    public void testErrorCodeType() throws EnumConstantNotExistsException {
        try {
            EnumUtil.getCodeByValue(ErrorCodeTypeEnum.class, "");
        } catch (EnumTransferException e) {
            System.out.println("error code type enum exception: " + e);
            throw e;
        }
    }

    @Test(expected = EnumTransferException.class)
    public void testErrorValueType() throws EnumConstantNotExistsException {
        try {
            EnumUtil.getCodeByValue(ErrorValueTypeEnum.class, "");
        } catch (EnumTransferException e) {
            System.out.println("error value type enum exception: " + e);
            throw e;
        }
    }

    @After
    public void after() {
        System.out.println("测试结束");
    }
}