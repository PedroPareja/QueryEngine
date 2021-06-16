package com.github.pedropareja.database.generic.querygen.groupby;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.having.QGLinkHaving;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;

public interface QGGroupBy
    extends QGQuery, QGLinkHaving, QGLinkOrderBy, QGLinkLimit, QGLinkOptional<QGGroupBy>
{}
