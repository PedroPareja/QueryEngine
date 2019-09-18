package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkSet extends QGLinkBase
{
    @SuppressWarnings("unchecked")
    default QGSet set(DBFieldInfo... fieldList)
    {
        return assignNext(new QGSetPrv(getInit(), fieldList));
    }
}
