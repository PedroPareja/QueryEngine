package es.pedropareja.database.generic.querygen.expression.casecmd;

import es.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.casecmd.when.QGWhen;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperators;

public interface QGCase
    extends QGExpression, QGLinkOperators, QGLinkAs
{
    QGWhen when();
    QGCase elseThen(QGExpression exp);
}
