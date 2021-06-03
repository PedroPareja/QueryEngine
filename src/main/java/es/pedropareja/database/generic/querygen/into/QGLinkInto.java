package es.pedropareja.database.generic.querygen.into;

import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

public interface QGLinkInto extends QGLinkBase
{
    default QGInto into(DBTable table)
    {
        return assignNext(new QGIntoPrv(table, getInit()));
    }

    default QGInto into(Class<? extends DBTable> tableType)
    {
        return into(QGQueryBase.getTableInstance(tableType));
    }
}
