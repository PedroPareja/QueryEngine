package es.pedropareja.database.generic.querygen.with.as;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.with.then.QGLinkWithThen;

public interface QGWithAs extends QGQuery, QGLinkWithThen
{
    QGWithAs unionAll(QGQuery recursiveClause);
}
