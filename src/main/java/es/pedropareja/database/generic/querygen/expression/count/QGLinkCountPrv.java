package es.pedropareja.database.generic.querygen.expression.count;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkCountPrv extends QGLinkCount, QGExpressionPrv
{
    @Override
    default QGCount count()
    {
        return assignNext(new QGCountPrv(getInit()));
    }

    @Override
    default QGCount count(QGExpression ... expressions)
    {
        return assignNext(new QGCountPrv(getInit(), expressions));
    }
}
