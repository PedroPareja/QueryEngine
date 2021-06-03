package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkAggregate
{
    QGAggregate avg(QGExpression exp);

    QGAggregate min(QGExpression exp);

    QGAggregate max(QGExpression exp);

    QGAggregate sum(QGExpression exp);
}
