package es.pedropareja.database.generic.querygen.merge.when;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.merge.when.QGMergeWhen.QGMergeWhenType;

public interface QGLinkMergeWhen extends QGLinkBase
{
    default QGMergeWhen when(QGMergeWhenType type) { return assignNext(new QGMergeWhenPrv(getInit(), type)); }
}
