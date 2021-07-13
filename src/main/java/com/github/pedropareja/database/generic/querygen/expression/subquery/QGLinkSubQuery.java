package com.github.pedropareja.database.generic.querygen.expression.subquery;

import com.github.pedropareja.database.generic.querygen.select.QGSelect;

public interface QGLinkSubQuery
{
    QGSubQuery subQuery(QGSelect query);
}
