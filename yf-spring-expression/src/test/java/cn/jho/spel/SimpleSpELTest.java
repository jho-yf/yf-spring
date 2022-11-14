package cn.jho.spel;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * SimpleSpELTest
 *
 * @author JHO xu-jihong@qq.com
 */
class SimpleSpELTest extends AbstractSpELTest {

    @Test
    void testSimpleString() {
        /*
         * 定义字符串：
         *  1. 调用方法substring(#start, #end)
         *  2. 调用方法toUpperCase()
         *  3. 两个变量：start、end
         */
        String str = "(\"jho-yf.github.io\").substring(#start, #end).toUpperCase()";

        // 定义SpEL解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 将字符串表达式解析成为Expression对象
        Expression expr = parser.parseExpression(str);
        // 定义表达式解析的上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("start", 0);
        context.setVariable("end", 6);
        // 表达式求值
        String result = (String) expr.getValue(context);
        LOGGER.info("处理结果:{}", result);
        assertEquals(result, "jho-yf.github.io".substring(0, 6).toUpperCase());
    }

    @Test
    void testPlus() {
        String str = "1+99";
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expr = parser.parseExpression(str);
        Object result = expr.getValue();
        LOGGER.info("处理结果:{}", result);

        assertNotNull(result);
        assertInstanceOf(Integer.class, result);
        assertEquals(result, 1 + 99);
    }


}
