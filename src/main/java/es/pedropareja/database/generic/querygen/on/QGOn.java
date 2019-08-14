package es.pedropareja.database.generic.querygen.on;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import es.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import es.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import es.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGOn
        extends QGQuery, QGLinkConditions<QGOn>, QGLinkWhere, QGLinkOrderBy, QGLinkLimit
{}
