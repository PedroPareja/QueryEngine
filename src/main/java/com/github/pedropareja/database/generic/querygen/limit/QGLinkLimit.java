package com.github.pedropareja.database.generic.querygen.limit;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkLimit extends QGLinkBase
{
    default QGLimit limit()
    {
        return assignNext(new QGLimitPrv(getInit()));
    }

    default QGLimit limit(int rowLimit)
    {
        return assignNext(new QGLimitPrv(rowLimit, getInit()));
    }
}
