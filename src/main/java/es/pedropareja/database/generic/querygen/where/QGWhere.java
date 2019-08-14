package es.pedropareja.database.generic.querygen.where;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;

public interface QGWhere
        extends QGQuery, QGLinkConditions<QGWhere>, QGLinkOrderBy, QGLinkLimit
{}
