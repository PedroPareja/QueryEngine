package es.pedropareja.database.generic.querygen.orderby;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.Order;
import es.pedropareja.database.generic.querygen.auto.QGAutoFields;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Arrays;
import java.util.List;

public class QGOrderByPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd
        implements QGOrderBy, QGLinkOptionalPrv<QGOrderBy, QGOrderByPrv<T>>, QGAutoFields
{
    private static final Order DEFAULT_ORDER = Order.ASC;

    private final T field;
    private final Order order;

    public QGOrderByPrv(T field, Order order, QGQueryInit init)
    {
        super(init);
        this.field = field;
        this.order = order;
    }

    public QGOrderByPrv(T field, QGQueryInit init)
    {
        this(field, DEFAULT_ORDER, init);
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" ORDER BY ");

        printField(stringBuilder, field, getInit().isFullNamespaces(), context);

        stringBuilder.append(" ").append(order.name());

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGOrderByPrv))
            return false;

        QGOrderByPrv<?> qOrderBy = (QGOrderByPrv<?>) q;

        return field.equalsField(qOrderBy.field) && order == qOrderBy.order;
    }

    @Override
    public QGOrderBy getThis()
    {
        return this;
    }

    @Override
    public QGOrderByPrv<T> getPrv()
    {
        return this;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(field);
    }
}
