package es.pedropareja.database.generic.querygen.into;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkInto extends QGLinkBase
{
    default <T extends Enum<?> & DBFieldInfo> QGInto into(Class<T> tableType)
    {
        return assignNext(new QGIntoPrv<>(tableType, getInit()));
    }
}
