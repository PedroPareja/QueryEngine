package com.github.pedropareja.database.generic.querygen.from;

import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.QGExprGen;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkFrom extends QGLinkBase
{
    default QGFrom from(QGExpression tableExp)
    {
        return assignNext(new QGFromPrv(tableExp, getInit()));
    }

    default QGFrom from(DBTable table)
    {
        return from(QGExprGen.table(table));
    }

    default QGFrom from(Class<? extends DBTable> tableType)
    {
        return from(QGQueryBase.getTableInstance(tableType));
    }
}
