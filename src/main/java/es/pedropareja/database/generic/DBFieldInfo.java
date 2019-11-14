package es.pedropareja.database.generic;

import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.Arrays;
import java.util.List;

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

    @Override
    default <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        QGQueryBase.printField(stringBuilder, this,  fullNamespaces, context);
    }

    @Override
    default List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(this);
    }
}
