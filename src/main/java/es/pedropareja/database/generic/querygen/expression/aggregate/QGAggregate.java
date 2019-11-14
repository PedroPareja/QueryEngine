package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGAggregate extends QGExpression, QGLinkAs
{
    enum Type
    {
        AVG,
        COUNT,
        MIN,
        MAX,
        SUM
    }
}
