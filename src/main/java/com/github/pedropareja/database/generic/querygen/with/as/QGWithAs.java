package com.github.pedropareja.database.generic.querygen.with.as;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.with.then.QGLinkWithThen;

public interface QGWithAs extends QGQuery, QGLinkWithThen
{
    QGWithAs unionAll(QGQuery recursiveClause);
}
