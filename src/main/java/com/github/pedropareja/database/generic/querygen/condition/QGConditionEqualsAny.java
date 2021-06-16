package com.github.pedropareja.database.generic.querygen.condition;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;

import java.util.Arrays;
import java.util.List;

public class QGConditionEqualsAny<T extends DBFieldInfo> implements QGConditionBase
{
    private final T field;

    public QGConditionEqualsAny(T field)
    {
        this.field = field;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");

        QGQueryBase.printField(stringBuilder, field, fullNamespaces, context);

        stringBuilder.append(" = ANY(?)");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionEqualsAny))
            return false;

        return field.equalsField(((QGConditionEqualsAny<?>)obj).field);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(field);
    }
}
