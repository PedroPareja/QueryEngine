package es.pedropareja.database.generic.querygen.expression.as;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkAsPrv extends QGLinkAs, QGExpressionPrv
{
    default QGAs as(String id)
    {
        return assignNext(new QGAsPrv(getInit(), id));
    }
}
