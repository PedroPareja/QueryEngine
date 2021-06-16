package com.github.pedropareja.database.generic.querygen.merge.insert;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeInsert extends QGLinkBase
{
    default QGMergeInsert insert(DBFieldInfo... fields)
    {
        return assignNext(new QGMergeInsertPrv(getInit(), fields));
    }
}
