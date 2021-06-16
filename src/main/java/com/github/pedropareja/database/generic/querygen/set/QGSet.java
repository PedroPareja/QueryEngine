package com.github.pedropareja.database.generic.querygen.set;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;
import com.github.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGSet
        extends QGQuery, QGLinkWhere, QGLinkOptional<QGSet>
{
    QGSet set(DBFieldInfo... fields);
    QGSet set(DBFieldInfo field, QGExpression value);
    QGSet set(QGSetAssignment... setAssignments);
}
