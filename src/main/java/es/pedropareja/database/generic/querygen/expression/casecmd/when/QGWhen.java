package es.pedropareja.database.generic.querygen.expression.casecmd.when;

import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.casecmd.QGCase;

public interface QGWhen extends QGExpression, QGLinkConditions<QGWhen>
{
    QGCase then(QGExpression exp);
}
