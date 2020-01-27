package es.pedropareja.database.generic.querygen.merge.insert.values;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkMergeInsertValues extends QGLinkBase
{
    default QGMergeInsertValues values(QGExpression... valExpressions)
    {
        return assignNext(new QGMergeInsertValuesPrv(getInit(), valExpressions));
    }
}
