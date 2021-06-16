package com.github.pedropareja.database.generic.querygen.having;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkHaving extends QGLinkBase
{
    default QGHaving having()
    {
        return assignNext(new QGHavingPrv(getInit()));
    }
}
