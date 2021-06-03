package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkAggregatePrv extends QGLinkAggregate, QGExpressionPrv
{
    default QGAggregate assignFunction(QGAggregate.Type type, QGExpression exp)
    {
        return assignNext(new QGAggregatePrv(getInit(), type, exp));
    }

    @Override
    default QGAggregate avg(QGExpression exp)
    {
        return assignFunction(QGAggregate.Type.AVG, exp);
    }

    @Override
    default QGAggregate min(QGExpression exp)
    {
        return assignFunction(QGAggregate.Type.MIN, exp);
    }

    @Override
    default QGAggregate max(QGExpression exp)
    {
        return assignFunction(QGAggregate.Type.MAX, exp);
    }

    @Override
    default QGAggregate sum(QGExpression exp)
    {
        return assignFunction(QGAggregate.Type.SUM, exp);
    }
}
