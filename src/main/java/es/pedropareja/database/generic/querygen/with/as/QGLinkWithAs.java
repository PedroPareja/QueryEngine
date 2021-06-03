package es.pedropareja.database.generic.querygen.with.as;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGLinkWithAs extends QGLinkBase
{
    default QGWithAs as(QGQuery anchorClause)
    {
        return assignNext(new QGWithAsPrv(anchorClause, getInit()));
    }
}
