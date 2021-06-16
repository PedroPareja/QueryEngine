package com.github.pedropareja.database.generic.querygen.merge.update;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeUpdate extends QGLinkBase
{
    default QGMergeUpdate update()
    {
        return assignNext(new QGMergeUpdatePrv(getInit()));
    }
}
