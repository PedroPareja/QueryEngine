package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGQuery extends QGInitReferenced, QGOptionalityEnabled, QGExpression
{
    <T> String toString(T context);
}
