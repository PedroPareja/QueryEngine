package es.pedropareja.database.generic.querygen.auto;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTableMapper;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkAuto extends QGLinkBase
{
    default <T extends Enum<?> & DBFieldInfo> QGAuto auto(DBTableMapper tableMapper, Class<T> mainTable)
    {
        return assignNext(new QGAutoPrv<>(tableMapper, mainTable, getInit()));
    }
}
