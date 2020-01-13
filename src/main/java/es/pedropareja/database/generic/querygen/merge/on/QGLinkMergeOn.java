package es.pedropareja.database.generic.querygen.merge.on;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeOn extends QGLinkBase
{
    default QGMergeOn on() { return assignNext(new QGMergeOnPrv(getInit())); }
}
