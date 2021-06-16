package com.github.pedropareja.database.generic.querygen.on;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import com.github.pedropareja.database.generic.querygen.groupby.QGLinkGroupBy;
import com.github.pedropareja.database.generic.querygen.join.QGLinkJoin;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import com.github.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGOn
        extends QGQuery, QGLinkConditions<QGOn>, QGLinkWhere, QGLinkGroupBy, QGLinkOrderBy, QGLinkLimit, QGLinkJoin
{}
