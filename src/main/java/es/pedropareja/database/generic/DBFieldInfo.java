package es.pedropareja.database.generic;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface DBFieldInfo extends QGExpression
{
    String getName();

    default <T extends DBFieldInfo> boolean equalsField(T other)
    {
        if(other == null)
            return false;

        if (!this.getClass().equals(other.getClass()))
            return false;

        return this == other;
    }
}
