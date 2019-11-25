package es.pedropareja.database.generic.querygen.expression;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.aggregate.QGAggregate;
import es.pedropareja.database.generic.querygen.expression.aggregate.QGAggregate.Type;
import es.pedropareja.database.generic.querygen.expression.aggregate.QGAggregatePrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.coalesce.QGCoalesce;
import es.pedropareja.database.generic.querygen.expression.coalesce.QGCoalescePrv;
import es.pedropareja.database.generic.querygen.expression.field.QGField;
import es.pedropareja.database.generic.querygen.expression.field.QGFieldPrv;
import es.pedropareja.database.generic.querygen.expression.id.QGId;
import es.pedropareja.database.generic.querygen.expression.id.QGIdPrv;
import es.pedropareja.database.generic.querygen.expression.number.QGNumber;
import es.pedropareja.database.generic.querygen.expression.number.QGNumberPrv;

import java.math.BigDecimal;

public class QGExprGen
{
    private QGExprGen() {}

    public static <T extends Enum<?> & DBFieldInfo> QGAggregate avg(T field)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.AVG, field);
    }

    public static QGAggregate avg(String id)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.AVG, id);
    }

    public static <T extends Enum<?> & DBFieldInfo> QGAggregate count(T field)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.COUNT, field);
    }

    public static QGAggregate count(String id)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.COUNT, id);
    }

    public static QGAggregate count() { return new QGAggregatePrv<>(Type.COUNT); }

    public static <T extends Enum<?> & DBFieldInfo> QGAggregate min(T field)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.MIN, field);
    }

    public static QGAggregate min(String id)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.MIN, id);
    }

    public static <T extends Enum<?> & DBFieldInfo> QGAggregate max(T field)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.MAX, field);
    }

    public static QGAggregate max(String id)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.MAX, id);
    }

    public static <T extends Enum<?> & DBFieldInfo> QGAggregate sum(T field)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.SUM, field);
    }

    public static QGAggregate sum(String id)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.SUM, id);
    }

    public static <T extends Enum<?> & DBFieldInfo> QGField field(T fieldValue)
    {
        return new QGFieldPrv<>(fieldValue);
    }

    public static QGId id(String idValue)
    {
        return new QGIdPrv(idValue);
    }

    @SafeVarargs
    public static QGCoalesce coalesce(QGExpression... parameters)
    {
        return new QGCoalescePrv(parameters);
    }

    public static QGNumber number(BigDecimal value)
    {
        return new QGNumberPrv(value);
    }

    public static QGNumber number(int value)
    {
        return new QGNumberPrv(value);
    }

    public static QGNumber number(String value)
    {
        return new QGNumberPrv(value);
    }
}
