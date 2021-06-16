package com.github.pedropareja.database.generic.querygen.condition;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;

import java.util.Arrays;
import java.util.List;

public class QGConditionIn implements QGConditionBase
{
    private final DBFieldInfo field;
    private final int numberOfParameters;

    public QGConditionIn(DBFieldInfo field, int numberOfParameters)
    {
        this.field = field;
        this.numberOfParameters = numberOfParameters;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(!isNull())
        {
            stringBuilder.append(" ");
            QGQueryBase.printField(stringBuilder, field, fullNamespaces, context);
            stringBuilder.append(" IN(");

            for (int i = 0; i < numberOfParameters; i++)
                stringBuilder.append(i != 0 ? ", " : "").append("?");

            stringBuilder.append(")");
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionIn))
            return false;

        QGConditionIn o = (QGConditionIn)obj;

        return field.equalsField(o.field) && numberOfParameters == o.numberOfParameters;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(field);
    }

    @Override
    public boolean isNull()
    {
        return numberOfParameters < 1;
    }
}
