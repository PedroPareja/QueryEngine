package com.github.pedropareja.database.generic;

import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface DBFieldInfo extends QGExpression
{
    String getName();

    default <T extends DBFieldInfo> boolean equalsField(T other)
    {
        if(other == null)
            return false;

        if (!this.getClass().equals(other.getClass()))
            return false;

        DBFieldInfo field = (DBFieldInfo) other;

        return Objects.equals(getParentTable(), field.getParentTable())
                && Objects.equals(getName(), field.getName());
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

    DBTable getParentTable();

    @Override
    default boolean isComplex()
    {
        return false;
    }
}
