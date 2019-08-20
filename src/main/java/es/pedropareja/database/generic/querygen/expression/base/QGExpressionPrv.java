package es.pedropareja.database.generic.querygen.expression.base;

public interface QGExpressionPrv extends QGExpression
{
    QGExpressionBase getInit();
    <T extends QGExpressionBase> T assignNext(T next);
}
