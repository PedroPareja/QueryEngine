package com.github.pedropareja.database.generic.querygen.expression.as;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.id.QGIdPrv;

public interface QGLinkAsPrv extends QGLinkAs, QGExpressionPrv
{
    default QGAs as(String id)
    {
        return assignNext(new QGAsPrv(getInit(), new QGIdPrv(id)));
    }
}
