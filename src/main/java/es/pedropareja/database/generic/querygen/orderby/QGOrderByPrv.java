package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.auto.QGAutoFields;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Arrays;
import java.util.List;

public class QGOrderByPrv extends QGQueryMiddleEnd
        implements QGOrderBy, QGLinkOptionalPrv<QGOrderBy>, QGAutoFields
{
    private static final Order DEFAULT_ORDER = Order.ASC;

    private final QGExpression exp;
    private final Order order;

    public QGOrderByPrv(QGExpression exp, Order order, QGQueryInit init)
    {
        super(init);
        this.exp = exp;
        this.order = order;
    }

    public QGOrderByPrv(QGExpression exp, QGQueryInit init)
    {
        this(exp, DEFAULT_ORDER, init);
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" ORDER BY ");

        exp.genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), context);

        stringBuilder.append(" ").append(order.name());

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
        return exp.getAutoFields();
    }
}
