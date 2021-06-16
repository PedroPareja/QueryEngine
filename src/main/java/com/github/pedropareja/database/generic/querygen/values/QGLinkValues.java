package com.github.pedropareja.database.generic.querygen.values;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkValues extends QGLinkBase
{
    default QGValues values(QGExpression... valueExpressions)
    {
        return assignNext(new QGValuesPrv(getInit(), valueExpressions));
    }

    default QGValues values(QGQuery select)
    {
        return assignNext(new QGValuesPrv(getInit(), select));
    }
}
