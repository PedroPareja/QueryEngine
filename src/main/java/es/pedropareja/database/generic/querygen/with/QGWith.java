package es.pedropareja.database.generic.querygen.with;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.with.as.QGLinkWithAs;

public interface QGWith
    extends QGQuery, QGLinkWithAs
{
    QGWith recursive();
    QGWith id(String id);
}
