package es.pedropareja.database.generic.querygen.expression.function;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkFunctionPrv extends QGLinkFunction, QGExpressionPrv
{
    @Override
    default QGFunction function(String functionName, QGExpression... parameters)
    {
        return assignNext(new QGFunctionPrv(getInit(), functionName, parameters));
    }
}
