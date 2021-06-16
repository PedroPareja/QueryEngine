package com.github.pedropareja.database.generic.querygen.merge.insert.values;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;

public interface QGLinkMergeInsertValues extends QGLinkBase
{
    default QGMergeInsertValues values(QGExpression... valExpressions)
    {
        return assignNext(new QGMergeInsertValuesPrv(getInit(), valExpressions));
    }
}
