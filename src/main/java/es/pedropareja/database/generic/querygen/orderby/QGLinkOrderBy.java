package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

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
}
