package es.pedropareja.database.generic.querygen.expression.number;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

import java.math.BigDecimal;

public interface QGLinkNumberPrv extends QGLinkNumber, QGExpressionPrv
{
    @Override
    default QGNumber number(BigDecimal value)
    {
        return assignNext(new QGNumberPrv(getInit(), value));
    }

    @Override
    default QGNumber number(int value)
    {
        return assignNext(new QGNumberPrv(getInit(), value));
    }

    @Override
    default QGNumber number(String value)
    {
        return assignNext(new QGNumberPrv(getInit(), value));
    }
}
