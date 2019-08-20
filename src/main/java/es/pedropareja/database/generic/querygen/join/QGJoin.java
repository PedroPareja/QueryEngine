package es.pedropareja.database.generic.querygen.join;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.on.QGLinkOn;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import es.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import es.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGJoin
        extends QGQuery, QGLinkOn, QGLinkWhere, QGLinkOrderBy, QGLinkLimit, QGLinkOptional<QGJoin>, QGLinkJoin
{}
