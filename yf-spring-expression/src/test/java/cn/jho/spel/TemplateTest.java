package cn.jho.spel;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * TemplateTest
 *
 * @author JHO xu-jihong@qq.com
 */
class TemplateTest extends AbstractSpELTest {

    @Test
    void testDefaultTmpl() {
        String str = "#{1+99}";
        SpelExpressionParser parser = new SpelExpressionParser();

        assertThrowsExactly(SpelParseException.class, () -> parser.parseExpression(str));

        Expression expr = parser.parseExpression(str, ParserContext.TEMPLATE_EXPRESSION);
        Object result = expr.getValue();
        assertInstanceOf(Integer.class, result);
        assertEquals(result, 1 + 99);
    }

    @Test
    void testCustomTmpl() {
        String str = "@[1+99]";
        SpelExpressionParser parser = new SpelExpressionParser();

        Expression expr = parser.parseExpression(str, new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "@[";
            }

            @Override
            public String getExpressionSuffix() {
                return "]";
            }
        });
        Object result = expr.getValue();
        assertInstanceOf(Integer.class, result);
        assertEquals(result, 1 + 99);
    }

}
