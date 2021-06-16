package com.github.pedropareja.database.generic.querygen.orderby;

import com.github.pedropareja.database.generic.querygen.Order;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkOrderBy extends QGLinkBase
{
    default QGOrderBy orderBy(QGExpression exp)
    {
        return assignNext(new QGOrderByPrv(exp, getInit()));
    }

    default QGOrderBy orderBy(QGExpression exp, Order order)
    {
        return assignNext(new QGOrderByPrv(exp, order, getInit()));
    }

    default QGOrderBy orderBy(QGOrderElement... orderElements)
    {
        return assignNext(new QGOrderByPrv(getInit(), orderElements));
    }
}
