package com.github.pedropareja.database.generic.querygen.orderby;

import com.github.pedropareja.database.generic.querygen.Order;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGOrderElement
{
    QGExpression getExp();
    Order getOrder();
}
