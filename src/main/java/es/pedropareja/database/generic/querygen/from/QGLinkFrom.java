package es.pedropareja.database.generic.querygen.from;

import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.QGExprGen;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

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
