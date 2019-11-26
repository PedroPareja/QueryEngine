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

    public static QGAggregate avg(QGExpression exp)
    {
        return new QGAggregatePrv<>(Type.AVG, exp);
    }

    public static QGAggregate count(QGExpression exp)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.COUNT, exp);
    }

    public static QGAggregate count() { return new QGAggregatePrv<>(Type.COUNT); }

    public static QGAggregate min(QGExpression exp)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.MIN, exp);
    }

    public static QGAggregate max(QGExpression exp)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.MAX, exp);
    }

    public static QGAggregate sum(QGExpression exp)
    {
        return new QGAggregatePrv<>(QGAggregate.Type.SUM, exp);
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
