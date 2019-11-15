package es.pedropareja.database.generic.querygen.merge.using;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGLinkMergeUsing extends QGLinkBase
{
    default QGMergeUsing using(QGQuery query)
    {
        return assignNext(new QGMergeUsingPrv(getInit(), query));
    }
}
