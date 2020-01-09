package es.pedropareja.database.generic.querygen.groupby;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkGroupBy extends QGLinkBase
{
    default QGGroupBy groupBy(QGExpression... expressions)
    {
        return assignNext(new QGGroupByPrv(getInit(), expressions));
    }
}
