package com.github.pedropareja.database.generic.querygen.auto;

import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.groupby.QGLinkGroupBy;
import com.github.pedropareja.database.generic.querygen.join.QGJoin.JoinType;
import com.github.pedropareja.database.generic.querygen.join.QGLinkJoin;
import com.github.pedropareja.database.generic.querygen.limit.QGLinkLimit;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import com.github.pedropareja.database.generic.querygen.orderby.QGLinkOrderBy;
import com.github.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGAuto extends QGQuery, QGLinkOptional<QGAuto>, QGLinkWhere, QGLinkOrderBy, QGLinkGroupBy, QGLinkLimit, QGLinkJoin
{
    @SuppressWarnings("unchecked")
    QGAuto ignoreTables(DBTable ... tables);

    @SuppressWarnings("unchecked")
    QGAuto ignoreTables(Class<? extends DBTable> ... tables);

    @SuppressWarnings("unchecked")
    QGAuto setLink(JoinType joinType, DBTable ... tables);

    @SuppressWarnings("unchecked")
    QGAuto setLink(JoinType joinType, Class<? extends DBTable> ... tables);
}
