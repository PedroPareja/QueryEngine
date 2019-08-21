package es.pedropareja.database.generic.querygen.expression;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.aggregate.QGAggregate;
import es.pedropareja.database.generic.querygen.expression.aggregate.QGAggregatePrv;
import es.pedropareja.database.generic.querygen.expression.field.QGField;
import es.pedropareja.database.generic.querygen.expression.field.QGFieldPrv;
import es.pedropareja.database.generic.querygen.expression.id.QGId;
import es.pedropareja.database.generic.querygen.expression.id.QGIdPrv;

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
}
