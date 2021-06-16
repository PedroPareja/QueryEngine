package com.github.pedropareja.database.generic.querygen.with.then;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkWithThen extends QGLinkBase
{
    default QGWithThen then(QGQuery mainQuery)
    {
        return assignNext(new QGWithThenPrv(mainQuery, getInit()));
    }
}
