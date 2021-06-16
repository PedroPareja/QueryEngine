package com.github.pedropareja.database.generic.querygen.condition;

import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGConditionBase extends QGAutoFields, QGExpression
{
    default boolean isNull()
    {
        return false;
    }

    @Override
    default boolean isComplex()
    {
        return true;
    }
}
