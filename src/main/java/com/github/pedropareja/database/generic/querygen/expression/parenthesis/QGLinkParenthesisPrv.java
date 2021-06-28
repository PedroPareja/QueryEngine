package com.github.pedropareja.database.generic.querygen.expression.parenthesis;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkParenthesisPrv extends QGLinkParenthesis, QGExpressionPrv
{
    @Override
    default QGParenthesis parenthesis(QGExpression content)
    {
        return assignNext(new QGParenthesisPrv(getInit(), content));
    }
}
