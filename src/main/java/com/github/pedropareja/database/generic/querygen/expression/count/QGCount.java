package com.github.pedropareja.database.generic.querygen.expression.count;

import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperators;

public interface QGCount extends QGExpression, QGLinkAs, QGLinkOperators
{
    QGCount distinct();
    QGCount and(QGExpression ... expressions);
}
