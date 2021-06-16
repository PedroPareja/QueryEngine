package com.github.pedropareja.database.generic.querygen.orderby;

import com.github.pedropareja.database.generic.querygen.Order;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;

public interface QGOrderBy
        extends QGQuery, QGLinkLimit, QGLinkOptional<QGOrderBy>
{
    QGOrderBy and(QGExpression exp, Order order);
    QGOrderBy and(QGExpression exp);
    QGOrderBy and(QGOrderElement... orderElements);
}
