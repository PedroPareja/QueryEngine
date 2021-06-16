package com.github.pedropareja.database.generic.querygen.merge.when;

import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;
import com.github.pedropareja.database.generic.querygen.merge.when.QGMergeWhen.QGMergeWhenType;

public interface QGLinkMergeWhen extends QGLinkBase
{
    default QGMergeWhen when(QGMergeWhenType type) { return assignNext(new QGMergeWhenPrv(getInit(), type)); }
}
