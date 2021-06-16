package com.github.pedropareja.database.generic.querygen.auto;

import com.github.pedropareja.database.generic.DBTableMapper;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

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
