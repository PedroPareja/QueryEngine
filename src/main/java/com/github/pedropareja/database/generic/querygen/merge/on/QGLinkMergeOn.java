package com.github.pedropareja.database.generic.querygen.merge.on;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeOn extends QGLinkBase
{
    default QGMergeOn on() { return assignNext(new QGMergeOnPrv(getInit())); }
}
