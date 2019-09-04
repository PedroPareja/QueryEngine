package es.pedropareja.database.generic.querygen.base;

public interface QGQuery
{
    QGQueryInit getInit();

    <T> String toString(T context);
}
