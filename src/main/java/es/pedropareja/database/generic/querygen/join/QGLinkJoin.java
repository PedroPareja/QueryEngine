package es.pedropareja.database.generic.querygen.join;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkJoin extends QGLinkBase
{
    default <T extends Enum<?> & DBFieldInfo> QGJoin join(Class<T> tableType)
    {
        return assignNext(new QGJoinPrv<>(tableType, getInit()));
    }
}
