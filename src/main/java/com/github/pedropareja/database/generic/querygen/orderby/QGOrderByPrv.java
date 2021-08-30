package com.github.pedropareja.database.generic.querygen.orderby;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.Order;
import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.ArrayList;
import java.util.List;

public class QGOrderByPrv extends QGQueryMiddleEnd
        implements QGOrderBy, QGLinkOptionalPrv<QGOrderBy>, QGAutoFields
{
    private final List<QGOrderElement> orderElements = new ArrayList<>();

    public QGOrderByPrv(QGExpression exp, Order order, QGQueryInit init)
    {
        super(init);
        this.orderElements.add(new QGOrderElementPrv(exp, order));
    }

    public QGOrderByPrv(QGExpression exp, QGQueryInit init)
    {
        this(exp, Order.DEFAULT_ORDER, init);
    }

    @SafeVarargs
    public QGOrderByPrv(QGQueryInit init, QGOrderElement ... elements)
    {
        super(init);

        for(QGOrderElement element: elements)
            orderElements.add(element);
    }

    @Override
    public QGOrderBy and(QGOrderElement ... elements)
    {
        for(QGOrderElement element: elements)
            orderElements.add(element);

        return this;
    }

    @Override
    public QGOrderBy and(QGExpression exp, Order order)
    {
        return and(new QGOrderElementPrv(exp, order));
    }

    @Override
    public QGOrderBy and(QGExpression exp)
    {
        return and(new QGOrderElementPrv(exp));
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" ORDER BY ");

        for(int i=0; i < orderElements.size(); i++)
        {
            if(i!=0)
                stringBuilder.append(", ");

            orderElements.get(i).getExp().genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), this, context);
            stringBuilder.append(" ").append(orderElements.get(i).getOrder().name());
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGOrderBy getThis()
    {
        return this;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = null;

        for(QGOrderElement orderElement: orderElements)
            result = QGQueryBase.joinLists(result, orderElement.getExp().getAutoFields());

        return result;
    }
}
