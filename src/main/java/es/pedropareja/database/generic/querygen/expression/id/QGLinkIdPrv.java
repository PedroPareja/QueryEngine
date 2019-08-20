package es.pedropareja.database.generic.querygen.expression.id;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkIdPrv extends QGLinkId, QGExpressionPrv
{
    @Override
    default QGId id(String value)
    {
        return assignNext(new QGIdPrv(getInit(), value));
    }
}
