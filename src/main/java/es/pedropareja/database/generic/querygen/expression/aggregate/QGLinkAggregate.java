package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.field.QGField;

public interface QGLinkAggregate extends QGField
{
    QGAggregate avg(QGExpression exp);

    QGAggregate count(QGExpression exp);
    QGAggregate count();

    QGAggregate min(QGExpression exp);

    QGAggregate max(QGExpression exp);

    QGAggregate sum(QGExpression exp);
}
