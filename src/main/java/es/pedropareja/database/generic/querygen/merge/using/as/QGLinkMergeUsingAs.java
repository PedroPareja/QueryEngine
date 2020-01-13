package es.pedropareja.database.generic.querygen.merge.using.as;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeUsingAs extends QGLinkBase
{
    default QGMergeUsingAs as(String id) { return assignNext(new QGMergeUsingAsPrv(getInit(), id)); }
}
