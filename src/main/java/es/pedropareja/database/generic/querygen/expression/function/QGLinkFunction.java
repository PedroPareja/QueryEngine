package es.pedropareja.database.generic.querygen.expression.function;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkFunction
{
    QGFunction function(String functionName, QGExpression... parameters);
}
