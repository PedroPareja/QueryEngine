package es.pedropareja.database.generic.querygen.merge.insert;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeInsert extends QGLinkBase
{
    default QGMergeInsert insert(DBFieldInfo... fields)
    {
        return assignNext(new QGMergeInsertPrv(getInit(), fields));
    }
}
