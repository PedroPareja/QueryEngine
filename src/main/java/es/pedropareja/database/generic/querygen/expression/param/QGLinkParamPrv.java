package es.pedropareja.database.generic.querygen.expression.param;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkParamPrv extends QGLinkParam, QGExpressionPrv
{
    default QGParam param() { return assignNext(new QGParamPrv(getInit())); }
}
