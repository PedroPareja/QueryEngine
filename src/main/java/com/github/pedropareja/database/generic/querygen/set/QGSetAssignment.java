package com.github.pedropareja.database.generic.querygen.set;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGSetAssignment
{
    DBFieldInfo getField();
    QGExpression getValue();
}
