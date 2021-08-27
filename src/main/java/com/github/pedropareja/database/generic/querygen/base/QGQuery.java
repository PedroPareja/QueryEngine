package com.github.pedropareja.database.generic.querygen.base;

import com.github.pedropareja.database.generic.DBTableAliasIndex;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGQuery extends QGInitReferenced, QGOptionalityEnabled, QGExpression
{
    <T> String toString(T context);
}
