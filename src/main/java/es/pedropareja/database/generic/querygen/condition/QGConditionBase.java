package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.querygen.auto.QGAutoFields;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

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
