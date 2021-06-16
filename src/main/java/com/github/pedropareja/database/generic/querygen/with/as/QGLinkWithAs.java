package com.github.pedropareja.database.generic.querygen.with.as;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkWithAs extends QGLinkBase
{
    default QGWithAs as(QGQuery anchorClause)
    {
        return assignNext(new QGWithAsPrv(anchorClause, getInit()));
    }
}
