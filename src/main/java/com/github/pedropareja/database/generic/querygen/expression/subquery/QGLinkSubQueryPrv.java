package com.github.pedropareja.database.generic.querygen.expression.subquery;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkSubQueryPrv extends QGLinkSubQuery, QGExpressionPrv
{
    @Override
    default QGSubQuery subQuery(QGQuery query)
    {
        return assignNext(new QGSubQueryPrv(getInit(), query));
    }
}
