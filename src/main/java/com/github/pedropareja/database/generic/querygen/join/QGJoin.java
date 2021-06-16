package com.github.pedropareja.database.generic.querygen.join;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.groupby.QGLinkGroupBy;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.on.QGLinkOn;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import com.github.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGJoin
        extends QGQuery, QGLinkOn, QGLinkWhere, QGLinkGroupBy, QGLinkOrderBy, QGLinkLimit, QGLinkOptional<QGJoin>, QGLinkJoin
{
    enum JoinType
    {
        INNER,
        LEFT,
        RIGHT,
        FULL
    }
}
