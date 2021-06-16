package com.github.pedropareja.database.generic.querygen.expression.aggregate;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkAggregate
{
    QGAggregate avg(QGExpression exp);

    QGAggregate min(QGExpression exp);

    QGAggregate max(QGExpression exp);

    QGAggregate sum(QGExpression exp);
}
