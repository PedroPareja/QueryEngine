package com.github.pedropareja.database.generic.querygen.expression.id;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkIdPrv extends QGLinkId, QGExpressionPrv
{
    @Override
    default QGId id(String value)
    {
        return assignNext(new QGIdPrv(getInit(), value));
    }
}
