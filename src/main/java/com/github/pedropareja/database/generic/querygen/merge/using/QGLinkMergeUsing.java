package com.github.pedropareja.database.generic.querygen.merge.using;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGLinkMergeUsing extends QGLinkBase
{
    default QGMergeUsing using(QGQuery query)
    {
        return assignNext(new QGMergeUsingPrv(getInit(), query));
    }
}
