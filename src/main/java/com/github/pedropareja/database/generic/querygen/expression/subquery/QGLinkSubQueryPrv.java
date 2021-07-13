package com.github.pedropareja.database.generic.querygen.expression.subquery;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.select.QGSelect;

public interface QGLinkSubQueryPrv extends QGLinkSubQuery, QGExpressionPrv
{
    @Override
    default QGSubQuery subQuery(QGSelect query)
    {
        return assignNext(new QGSubQueryPrv(getInit(), query));
    }
}
