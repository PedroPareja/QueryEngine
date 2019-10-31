package es.pedropareja.database.generic.querygen.base;

public interface QGQuery extends QGInitReferenced, QGOptionalityEnabled
{
    <T> String toString(T context);
}
