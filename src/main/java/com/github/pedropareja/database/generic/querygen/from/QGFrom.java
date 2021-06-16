package com.github.pedropareja.database.generic.querygen.from;

import com.github.pedropareja.database.generic.querygen.join.QGLinkJoin;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.groupby.QGLinkGroupBy;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import com.github.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGFrom
        extends QGQuery, QGLinkWhere, QGLinkJoin, QGLinkGroupBy, QGLinkOrderBy, QGLinkLimit, QGLinkOptional<QGFrom>
{}
