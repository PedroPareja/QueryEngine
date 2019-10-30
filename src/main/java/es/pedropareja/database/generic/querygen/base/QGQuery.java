package es.pedropareja.database.generic.querygen.base;

public interface QGQuery extends QGOptionalityEnabled
{
    QGQueryInit getInit();

    <T> String toString(T context);
}
