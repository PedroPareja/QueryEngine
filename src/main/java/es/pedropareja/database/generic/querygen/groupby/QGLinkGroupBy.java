package es.pedropareja.database.generic.querygen.groupby;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkGroupBy extends QGLinkBase
{
    default <T extends Enum<?> & DBFieldInfo> QGGroupBy groupBy(T... fields)
    {
        return assignNext(new QGGroupByPrv<>(getInit(), fields));
    }
}
