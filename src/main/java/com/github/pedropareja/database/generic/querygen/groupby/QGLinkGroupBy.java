package com.github.pedropareja.database.generic.querygen.groupby;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkGroupBy extends QGLinkBase
{
    default QGGroupBy groupBy(QGExpression... expressions)
    {
        return assignNext(new QGGroupByPrv(getInit(), expressions));
    }
}
