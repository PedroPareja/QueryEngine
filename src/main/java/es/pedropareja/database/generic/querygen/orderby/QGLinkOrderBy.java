package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkOrderBy extends QGLinkBase
{
    default <T extends Enum<?> & DBFieldInfo> QGOrderBy orderBy(T field)
    {
        return assignNext(new QGOrderByPrv<>(field, getInit()));
    }

    default <T extends Enum<?> & DBFieldInfo> QGOrderBy orderBy(T field, Order order)
    {
        return assignNext(new QGOrderByPrv<>(field, order, getInit()));
    }
}
