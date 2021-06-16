package com.github.pedropareja.database.generic.querygen.having;

import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;

public interface QGHaving
    extends QGQuery, QGLinkConditions<QGHaving>, QGLinkOrderBy, QGLinkLimit
{}
