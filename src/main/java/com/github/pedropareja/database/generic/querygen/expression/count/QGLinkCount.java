package com.github.pedropareja.database.generic.querygen.expression.count;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkCount
{
    QGCount count();
    QGCount count(QGExpression... expressions);
}
