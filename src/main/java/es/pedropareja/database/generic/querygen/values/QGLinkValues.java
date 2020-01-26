package es.pedropareja.database.generic.querygen.values;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkValues extends QGLinkBase
{
    default QGValues values(QGExpression... valueExpressions)
    {
        return assignNext(new QGValuesPrv(getInit(), valueExpressions));
    }
}
