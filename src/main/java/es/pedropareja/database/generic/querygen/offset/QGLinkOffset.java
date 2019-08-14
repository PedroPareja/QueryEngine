package es.pedropareja.database.generic.querygen.offset;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkOffset extends QGLinkBase
{
    default QGOffset offset()
    {
        return assignNext(new QGOffsetPrv(getInit()));
    }

    default QGOffset offset(int offsetValue)
    {
        return assignNext(new QGOffsetPrv(offsetValue, getInit()));
    }
}
