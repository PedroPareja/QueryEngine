package com.github.pedropareja.database.generic.querygen.into;

import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

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
