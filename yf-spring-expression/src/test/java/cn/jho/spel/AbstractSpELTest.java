package cn.jho.spel;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * AbstractSpELTest
 *
 * @author JHO xu-jihong@qq.com
 */
abstract class AbstractSpELTest extends Assertions {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SimpleSpELTest.class);

    protected Object spEL(String expr) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expr, ParserContext.TEMPLATE_EXPRESSION);
        return expression.getValue(new StandardEvaluationContext());
    }

    protected <T> T spEL(String expr, Class<T> desiredResultType) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expr, ParserContext.TEMPLATE_EXPRESSION);
        return expression.getValue(new StandardEvaluationContext(), desiredResultType);
    }

    protected Object spEL(String expr, EvaluationContext context) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expr, ParserContext.TEMPLATE_EXPRESSION);
        return expression.getValue(context);
    }

}
