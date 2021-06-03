package es.pedropareja.database.generic.querygen.expression.count;

import es.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperators;

public interface QGCount extends QGExpression, QGLinkAs, QGLinkOperators
{
    QGCount distinct();
    QGCount and(QGExpression ... expressions);
}
