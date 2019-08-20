package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkAggregatePrv extends QGLinkAggregate, QGExpressionPrv
{
    default <T extends Enum<?> & DBFieldInfo> QGAggregate assignFunction(QGAggregate.Type type, T field)
    {
        return assignNext(new QGAggregatePrv<>(getInit(), type, field));
    }

    default QGAggregate assignFunction(QGAggregate.Type type, String id)
    {
        return assignNext(new QGAggregatePrv<>(getInit(), type, id));
    }

    default <T extends Enum<?> & DBFieldInfo> QGAggregate avg(T field)
    {
        return assignFunction(QGAggregate.Type.AVG, field);
    }

    default QGAggregate avg(String id)
    {
        return assignFunction(QGAggregate.Type.AVG, id);
    }

    default <T extends Enum<?> & DBFieldInfo> QGAggregate count(T field)
    {
        return assignFunction(QGAggregate.Type.COUNT, field);
    }

    default QGAggregate count(String id)
    {
        return assignFunction(QGAggregate.Type.COUNT, id);
    }

    default QGAggregate count()
    {
        return assignFunction(QGAggregate.Type.COUNT, "*");
    }

    default <T extends Enum<?> & DBFieldInfo> QGAggregate min(T field)
    {
        return assignFunction(QGAggregate.Type.MIN, field);
    }

    default QGAggregate min(String id)
    {
        return assignFunction(QGAggregate.Type.MIN, id);
    }

    default <T extends Enum<?> & DBFieldInfo> QGAggregate max(T field)
    {
        return assignFunction(QGAggregate.Type.MAX, field);
    }

    default QGAggregate max(String id)
    {
        return assignFunction(QGAggregate.Type.MAX, id);
    }

    default <T extends Enum<?> & DBFieldInfo> QGAggregate sum(T field)
    {
        return assignFunction(QGAggregate.Type.SUM, field);
    }

    default QGAggregate sum(String id)
    {
        return assignFunction(QGAggregate.Type.SUM, id);
    }
}
