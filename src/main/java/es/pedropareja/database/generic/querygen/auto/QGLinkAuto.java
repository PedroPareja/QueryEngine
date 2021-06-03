package es.pedropareja.database.generic.querygen.auto;

import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.DBTableMapper;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

public interface QGLinkAuto extends QGLinkBase
{
    default QGAuto auto(DBTableMapper tableMapper, DBTable mainTable)
    {
        return assignNext(new QGAutoPrv(tableMapper, mainTable, getInit()));
    }

    default QGAuto auto(DBTableMapper tableMapper, Class<? extends DBTable> mainTable)
    {
        return assignNext(new QGAutoPrv(tableMapper, QGQueryBase.getTableInstance(mainTable), getInit()));
    }
}
