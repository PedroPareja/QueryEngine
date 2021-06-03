package es.pedropareja.database.generic.querygen.join;

import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.QGExprGen;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.join.QGJoin.JoinType;

public interface QGLinkJoin extends QGLinkBase
{
    default QGJoin join(QGExpression tableExp)
    {
        return assignNext(new QGJoinPrv(tableExp, getInit()));
    }

    default QGJoin join(DBTable table)
    {
        return join(QGExprGen.table(table));
    }

    default QGJoin join(QGExpression tableExp, JoinType joinType)
    {
        return assignNext(new QGJoinPrv(tableExp, getInit(), joinType));
    }

    default QGJoin join(DBTable table, JoinType joinType)
    {
        return join(QGExprGen.table(table), joinType);
    }

    default QGJoin join(Class<? extends DBTable> tableType)
    {
        return join(QGQueryBase.getTableInstance(tableType));
    }

    default QGJoin join(Class<? extends DBTable> tableType, JoinType joinType)
    {
        return join(QGQueryBase.getTableInstance(tableType), joinType);
    }
}
