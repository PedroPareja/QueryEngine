package com.github.pedropareja.database.generic.querygen.select;

import com.github.pedropareja.database.generic.DBTableAliasIndex;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.from.QGLinkFrom;
import com.github.pedropareja.database.generic.querygen.auto.QGLinkAuto;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGSelect
        extends QGQuery, QGLinkFrom, QGLinkAuto
{
    QGSelect distinct();
    QGSelect and(QGExpression... expressions);
    QGSelect using(DBTableAliasIndex tableAliasIndex);
}
