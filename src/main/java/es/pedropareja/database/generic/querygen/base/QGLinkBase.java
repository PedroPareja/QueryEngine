package es.pedropareja.database.generic.querygen.base;

public interface QGLinkBase
{
    QGQueryInit getInit();
    <T extends QGQueryMiddleEnd> T assignNext(T queryMiddleEnd);
}
