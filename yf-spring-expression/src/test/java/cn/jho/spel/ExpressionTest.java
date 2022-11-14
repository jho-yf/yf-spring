package cn.jho.spel;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.SpelEvaluationException;

/**
 * ExpressionTest
 *
 * @author JHO xu-jihong@qq.com
 */
class ExpressionTest extends AbstractSpELTest {

    /**
     * 字面表达式
     */
    @Test
    void testStringLiteral() {
        assertDoesNotThrow(() -> {
            LOGGER.info("【'Hello' + \"World\"】字面表达式：{} 对象类型：{}", spEL("'Hello ' + \"World\""),
                    spEL("'Hello ' + \"World\"").getClass());
            LOGGER.info("【#{'Hello' + \"World\"}】字面表达式：{} 对象类型：{}", spEL("#{'Hello ' + \"World\"}"),
                    spEL("#{'Hello ' + \"World\"}").getClass());
            LOGGER.info("【#{1}】字面表达式：{} 对象类型：{}", spEL("#{1}"), spEL("#{1}").getClass());
            LOGGER.info("【#{1.1}】字面表达式：{} 对象类型：{}", spEL("#{1.1}"), spEL("#{1.1}").getClass());
            LOGGER.info("【#{1.2E10}】字面表达式：{} 对象类型：{}", spEL("#{1.2E10}"), spEL("#{1.2E10}").getClass());
            LOGGER.info("【#{true}】字面表达式：{} 对象类型：{}", spEL("#{true}"), spEL("#{true}").getClass());
        });
    }

    /**
     * 数学表达式
     */
    @Test
    void testOp() {
        assertDoesNotThrow(() -> {
            LOGGER.info("【#{ 1 + 2 - 3 * 4 / 5 }】数学表达式：{} 对象类型：{}", spEL("#{ 1 + 2 - 3 * 4 / 5 }"),
                    spEL("#{ 1 + 2 - 3 * 4 / 5 }").getClass());
            LOGGER.info("【#{ 1 + 2 - 3.0 * 4 / 5 }】数学表达式：{} 对象类型：{}", spEL("#{ 1 + 2 - 3.0 * 4 / 5 }"),
                    spEL("#{ 1 + 2 - 3.0 * 4 / 5 }").getClass());
            LOGGER.info("【#{ 10 % 3 }】数学表达式：{} 对象类型：{}", spEL("#{ 10 % 3 }"), spEL("#{ 10 % 3 }").getClass());
            LOGGER.info("【#{ 10 mod 3 }】数学表达式：{} 对象类型：{}", spEL("#{ 10 mod 3 }"), spEL("#{ 10 mod 3 }").getClass());
            LOGGER.info("【#{ 10 div 3 }】数学表达式：{} 对象类型：{}", spEL("#{ 10 div 3 }"), spEL("#{ 10 div 3 }").getClass());
        });
    }

    /**
     * 关系表达式
     */
    @Test
    void testRelational() {
        assertDoesNotThrow(() -> {
            LOGGER.info("【#{ 30 != 20 }】关系表达式：{} 对象类型：{}", spEL("#{ 30 != 20 }"), spEL("#{ 30 != 20 }").getClass());
            LOGGER.info("【#{ 30 NE 20 }】关系表达式：{} 对象类型：{}", spEL("#{ 30 NE 20 }"), spEL("#{ 30 NE 20 }").getClass());
            LOGGER.info("【#{ 30 EQ 20 }】关系表达式：{} 对象类型：{}", spEL("#{ 30 EQ 20 }"), spEL("#{ 30 EQ 20 }").getClass());
            LOGGER.info("【#{ 10 BETWEEN {0, 100} }】关系表达式：{} 对象类型：{}", spEL("#{ 10 BETWEEN {0, 100} }"),
                    spEL("#{ 10 BETWEEN {0, 100} }").getClass());
            LOGGER.info("【#{ 'h' BETWEEN {'a', 'z'} }】关系表达式：{} 对象类型：{}", spEL("#{ 'h' BETWEEN {'a', 'z'} }"),
                    spEL("#{ 'h' BETWEEN {'a', 'z'} }").getClass());
        });
    }

    /**
     * 逻辑表达式 - 与或非
     */
    @Test
    void testLogic() {
        assertDoesNotThrow(() -> {
            LOGGER.info("【#{ 30 != 20 || 10 EQ 10 }】逻辑表达式：{} 对象类型：{}", spEL("#{ 30 != 20 || 10 EQ 10 }"),
                    spEL("#{ 30 != 20 || 10 EQ 10 }").getClass());
            LOGGER.info("【#{ 30 != 20 && 10 EQ 10 }】逻辑表达式：{} 对象类型：{}", spEL("#{ 30 != 20 && 10 EQ 10 }"),
                    spEL("#{ 30 != 20 && 10 EQ 10 }").getClass());
            LOGGER.info("【#{ 30 != 20 AND 10 EQ 10 }】逻辑表达式：{} 对象类型：{}", spEL("#{ 30 != 20 AND 10 EQ 10 }"),
                    spEL("#{ 30 != 20 AND 10 EQ 10 }").getClass());
        });
    }

    /**
     * 三目运算
     */
    @Test
    void testTriOp() {
        assertDoesNotThrow(() -> {
            LOGGER.info("【#{ 1 > 0 ? '大于0' : '小于等于0' }】三目运算：{}", spEL("#{ 1 > 0 ? '大于0' : '小于等于0' }"));
            LOGGER.info("【#{ true ? '真' : '假' }】三目运算：{}", spEL("#{ true ? '真' : '假' }"));
            LOGGER.info("【#{ null ?: '空' }】三目运算：{}", spEL("#{ null ?: '空' }"));
        });

        assertThrowsExactly(SpelEvaluationException.class,
                () -> LOGGER.info("【#{ null ? '空' : '非空' }】三目运算：{}", spEL("#{ null ? '空' : '非空' }")),
                "EL1001E: Type conversion problem, cannot convert from null to boolean");
    }

    /**
     * 字符串处理
     */
    @Test
    void testStringOp() {
        assertEquals("h", spEL("#{ 'jho-yf.github.io'[1] }"));
        assertEquals("jho-yf", spEL("#{ 'jho-yf.github.io'.substring(0, 6) }"));
        assertEquals("jho-yf.github.com", spEL("#{ 'jho-yf.github.io'.replaceAll('io', 'com') }"));
        // 正则支持
        assertEquals(true, spEL("#{ '1285382691'.matches('[1-9][0-9]{4,}') }"));
    }

    /**
     * Class表达式
     */
    @Test
    void testClass() {
        assertEquals(String.class, spEL("#{ T(java.lang.String) }"));
        assertEquals(Date.class, spEL("#{ T(java.util.Date) }"));

        // 调用静态方法
        assertEquals(666, spEL("#{ T(java.lang.Integer).parseInt('666') }"));
        // 常量
        assertEquals(2147483647, spEL("#{ T(java.lang.Integer).MAX_VALUE }"));

        // 实例化对象
        assertEquals("Book{name='Spring in action', price=66.6}",
                spEL("#{ new cn.jho.pojo.Book('Spring in action', 66.6) }").toString());
    }

}
