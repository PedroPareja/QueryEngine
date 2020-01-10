package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;

public interface QGOrderBy
        extends QGQuery, QGLinkLimit, QGLinkOptional<QGOrderBy>
{
    QGOrderBy and(QGExpression exp, Order order);
    QGOrderBy and(QGExpression exp);
    QGOrderBy and(QGOrderElement ... orderElements);
}
