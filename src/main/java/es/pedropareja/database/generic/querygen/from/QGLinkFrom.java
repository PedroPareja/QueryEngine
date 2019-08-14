package es.pedropareja.database.generic.querygen.from;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkFrom extends QGLinkBase
{
    default <U extends Enum<?> & DBFieldInfo> QGFrom from(Class<U> tableType)
    {
        return assignNext(new QGFromPrv<>(tableType, getInit()));
    }
}
