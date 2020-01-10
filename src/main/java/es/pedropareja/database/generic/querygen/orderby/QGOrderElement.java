package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGOrderElement
{
    QGExpression getExp();
    Order getOrder();
}
