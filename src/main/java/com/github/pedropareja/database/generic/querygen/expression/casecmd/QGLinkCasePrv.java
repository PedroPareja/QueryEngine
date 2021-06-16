package com.github.pedropareja.database.generic.querygen.expression.casecmd;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkCasePrv extends QGLinkCase, QGExpressionPrv
{
    @Override
    default QGCase caseSelection()
    {
        return assignNext(new QGCasePrv(getInit()));
    }
}
