package com.github.pedropareja.database.generic.querygen.expression.field;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkFieldPrv extends QGLinkField, QGExpressionPrv
{
    @Override
    default QGField field(DBFieldInfo fieldInfo)
    {
        return assignNext(new QGFieldPrv(getInit(), fieldInfo));
    }
}
