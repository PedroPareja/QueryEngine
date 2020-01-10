package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public class QGOrderElementPrv implements QGOrderElement
{
    private final QGExpression exp;
    private final Order order;

    public QGOrderElementPrv(QGExpression exp, Order order)
    {
        this.exp = exp;
        this.order = order;
    }

    public QGOrderElementPrv(QGExpression exp)
    {
        this(exp, Order.DEFAULT_ORDER);
    }

    @Override
    public QGExpression getExp()
    {
        return exp;
    }

    @Override
    public Order getOrder()
    {
        return order;
    }
}
