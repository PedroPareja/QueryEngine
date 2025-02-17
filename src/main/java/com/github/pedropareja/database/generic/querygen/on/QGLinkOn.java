package com.github.pedropareja.database.generic.querygen.on;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkOn extends QGLinkBase
{
    default QGOn on()
    {
        return assignNext(new QGOnPrv(getInit()));
    }
}
