package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperators;

public interface QGAggregate extends QGExpression, QGLinkAs, QGLinkOperators
{
    enum Type
    {
        AVG,
        MIN,
        MAX,
        SUM
    }
}
