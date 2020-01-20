package es.pedropareja.database.generic.querygen.merge.update;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeUpdate extends QGLinkBase
{
    default QGMergeUpdate update()
    {
        return assignNext(new QGMergeUpdatePrv(getInit()));
    }
}
