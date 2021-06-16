package com.github.pedropareja.database.generic.querygen.where;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import com.github.pedropareja.database.generic.querygen.groupby.QGLinkGroupBy;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;

public interface QGWhere
        extends QGQuery, QGLinkConditions<QGWhere>, QGLinkGroupBy, QGLinkOrderBy, QGLinkLimit
{}
