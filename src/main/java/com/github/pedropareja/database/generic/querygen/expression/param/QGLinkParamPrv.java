package com.github.pedropareja.database.generic.querygen.expression.param;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkParamPrv extends QGLinkParam, QGExpressionPrv
{
    default QGParam param() { return assignNext(new QGParamPrv(getInit())); }

    default QGParam params(int repetitions) { return assignNext(new QGParamPrv(getInit(), repetitions)); }
}
