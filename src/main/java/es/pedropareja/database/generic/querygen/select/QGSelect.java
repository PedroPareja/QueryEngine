package es.pedropareja.database.generic.querygen.select;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.from.QGLinkFrom;

public interface QGSelect
        extends QGQuery, QGLinkFrom
{
    QGSelect distinct();
}
