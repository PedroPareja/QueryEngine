package com.github.pedropareja.database.generic.querygen.expression.coalesce;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkCoalescePrv extends QGLinkCoalesce, QGExpressionPrv
{
    @Override
    default QGCoalesce coalesce(QGExpression... parameters)
    {
        return assignNext(new QGCoalescePrv(getInit(), parameters));
    }
}
