package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;

public interface QGOrderBy
        extends QGQuery, QGLinkLimit, QGLinkOptional<QGOrderBy>
{}
