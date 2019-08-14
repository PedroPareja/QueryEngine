package es.pedropareja.database.generic.querygen.where;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkWhere extends QGLinkBase
{
    default QGWhere where()
    {
        return assignNext(new QGWherePrv(getInit()));
    }
}
