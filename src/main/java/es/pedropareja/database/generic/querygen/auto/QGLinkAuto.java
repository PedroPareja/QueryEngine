package es.pedropareja.database.generic.querygen.auto;

import es.pedropareja.database.generic.DBTableMapper;
import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkAuto extends QGLinkBase
{
    default QGAuto auto(DBTableMapper tableMapper)
    {
        return assignNext(new QGAutoPrv(tableMapper, getInit()));
    }
}
