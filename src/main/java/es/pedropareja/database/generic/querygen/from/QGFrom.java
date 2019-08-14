package es.pedropareja.database.generic.querygen.from;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.join.QGLinkJoin;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import es.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import es.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGFrom
        extends QGQuery, QGLinkWhere, QGLinkJoin, QGLinkOrderBy, QGLinkLimit, QGLinkOptional<QGFrom>
{}
