package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGAggregate extends QGExpression
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
