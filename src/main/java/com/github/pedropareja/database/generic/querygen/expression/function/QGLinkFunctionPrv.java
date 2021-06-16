package com.github.pedropareja.database.generic.querygen.expression.function;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkFunctionPrv extends QGLinkFunction, QGExpressionPrv
{
    @Override
    default QGFunction function(String functionName, QGExpression... parameters)
    {
        return assignNext(new QGFunctionPrv(getInit(), functionName, parameters));
    }
}
