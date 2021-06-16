package com.github.pedropareja.database.generic.querygen.where;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkWhere extends QGLinkBase
{
    default QGWhere where()
    {
        return assignNext(new QGWherePrv(getInit()));
    }
}
