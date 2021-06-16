package com.github.pedropareja.database.generic.querygen.values;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGValues extends QGQuery
{
    QGValues and(QGExpression... values);
}
