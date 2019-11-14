package es.pedropareja.database.generic.querygen.groupby;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import es.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;

public interface QGGroupBy
    extends QGQuery, QGLinkOrderBy, QGLinkLimit, QGLinkOptional<QGGroupBy>
{}
