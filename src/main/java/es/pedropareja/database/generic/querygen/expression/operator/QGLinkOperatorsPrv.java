package es.pedropareja.database.generic.querygen.expression.operator;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkOperatorsPrv extends QGLinkOperators, QGExpressionPrv
{
    @Override
    default QGOperator plus()
    {
        return assignNext(new QGOperatorPrv(getInit(), QGOperator.Type.PLUS));
    }

    @Override
    default QGOperator minus()
    {
        return assignNext(new QGOperatorPrv(getInit(), QGOperator.Type.MINUS));
    }

    @Override
    default QGOperator multiply()
    {
        return assignNext(new QGOperatorPrv(getInit(), QGOperator.Type.MULTIPLY));
    }

    @Override
    default QGOperator divide()
    {
        return assignNext(new QGOperatorPrv(getInit(), QGOperator.Type.DIVIDE));
    }
}
