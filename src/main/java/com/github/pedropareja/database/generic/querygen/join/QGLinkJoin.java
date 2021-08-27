package com.github.pedropareja.database.generic.querygen.join;

import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.QGExprGen;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.join.QGJoin.JoinType;

public interface QGLinkJoin extends QGLinkBase
{
    default QGJoin join(QGExpression tableExp)
    {
        return assignNext(new QGJoinPrv(tableExp, getInit()));
    }

    default QGJoin join(DBTable table)
    {
        String alias = getInit().getTableAliasIndex() != null ? getInit().getTableAliasIndex().getAlias(table) : null;

        return alias != null
               ? join(QGExprGen.table(table, alias))
               : join(QGExprGen.table(table));
    }

    default QGJoin join(QGExpression tableExp, JoinType joinType)
    {
        return assignNext(new QGJoinPrv(tableExp, getInit(), joinType));
    }

    default QGJoin join(DBTable table, JoinType joinType)
    {
        String alias = getInit().getTableAliasIndex() != null ? getInit().getTableAliasIndex().getAlias(table) : null;

        return alias != null
               ? join(QGExprGen.table(table, alias), joinType)
               : join(QGExprGen.table(table), joinType);
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
