package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkSet extends QGLinkBase
{
    @SuppressWarnings("unchecked")
    default <T extends Enum<?> & DBFieldInfo> QGSet set(T... fieldList)
    {
        return assignNext(new QGSetPrv<>(getInit(), fieldList));
    }
}
