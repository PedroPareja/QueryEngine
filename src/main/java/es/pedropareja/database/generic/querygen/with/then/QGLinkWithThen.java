package es.pedropareja.database.generic.querygen.with.then;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGLinkWithThen extends QGLinkBase
{
    default QGWithThen then(QGQuery mainQuery)
    {
        return assignNext(new QGWithThenPrv(mainQuery, getInit()));
    }
}
