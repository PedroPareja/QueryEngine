package es.pedropareja.database.generic.querygen.expression.count;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkCount
{
    QGCount count();
    QGCount count(QGExpression ... expressions);
}
