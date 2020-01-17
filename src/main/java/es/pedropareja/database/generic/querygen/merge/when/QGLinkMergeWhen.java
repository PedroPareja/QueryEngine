package es.pedropareja.database.generic.querygen.merge.when;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeWhen extends QGLinkBase
{
    default QGMergeWhen when() { return assignNext(new QGMergeWhenPrv(getInit())); }
}
