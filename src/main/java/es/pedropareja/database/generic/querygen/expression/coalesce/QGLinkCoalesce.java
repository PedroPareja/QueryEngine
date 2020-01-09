package es.pedropareja.database.generic.querygen.expression.coalesce;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkCoalesce
{
    QGCoalesce coalesce(QGExpression... parameters);
}
