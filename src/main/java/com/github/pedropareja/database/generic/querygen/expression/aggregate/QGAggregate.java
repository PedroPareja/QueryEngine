package com.github.pedropareja.database.generic.querygen.expression.aggregate;

import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperators;

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
