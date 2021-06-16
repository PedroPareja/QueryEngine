package com.github.pedropareja.database.generic.querygen.expression.count;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkCountPrv extends QGLinkCount, QGExpressionPrv
{
    @Override
    default QGCount count()
    {
        return assignNext(new QGCountPrv(getInit()));
    }

    @Override
    default QGCount count(QGExpression... expressions)
    {
        return assignNext(new QGCountPrv(getInit(), expressions));
    }
}
