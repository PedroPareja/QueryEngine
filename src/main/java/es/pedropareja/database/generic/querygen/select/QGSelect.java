package es.pedropareja.database.generic.querygen.select;

import es.pedropareja.database.generic.querygen.auto.QGLinkAuto;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.from.QGLinkFrom;

public interface QGSelect
        extends QGQuery, QGLinkFrom, QGLinkAuto
{
    QGSelect distinct();
    QGSelect and(QGExpression... expressions);
}
