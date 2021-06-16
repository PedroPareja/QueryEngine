package com.github.pedropareja.database.generic.querygen.expression.coalesce;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkCoalesce
{
    QGCoalesce coalesce(QGExpression... parameters);
}
