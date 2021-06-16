package com.github.pedropareja.database.generic.querygen.set;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGLinkBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGLinkSet extends QGLinkBase
{
    default QGSet set() { return assignNext(new QGSetPrv(getInit())); }

    @SuppressWarnings("unchecked")
    default QGSet set(DBFieldInfo... fieldList)
    {
        return assignNext(new QGSetPrv(getInit(), fieldList));
    }

    default QGSet set(DBFieldInfo field, QGExpression value)
    {
        return assignNext(new QGSetPrv(getInit(), field, value));
    }

    default QGSet set(QGSetAssignment... setAssignments)
    {
        return assignNext(new QGSetPrv(getInit(), setAssignments));
    }
}
