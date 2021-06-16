package com.github.pedropareja.database.generic.querygen.condition;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.List;
import java.util.Objects;

public class QGConditionComparation implements QGConditionBase
{
    private final ComparationType type;
    private final QGExpression exp1;
    private final QGExpression exp2;

    public QGConditionComparation(ComparationType type, QGExpression exp1, QGExpression exp2)
    {
        this.type = type;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public QGConditionComparation(ComparationType type, QGExpression exp)
    {
        this(type, exp, null);
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");

        if(exp1.isComplex())
            stringBuilder.append("(");

        exp1.genExpressionOutput(stringBuilder, fullNamespaces, context);

        if(exp1.isComplex())
            stringBuilder.append(")");

        stringBuilder.append(" ").append(type.symbol).append(" ");

        if(exp2 != null)
        {
            if(exp2.isComplex())
                stringBuilder.append("(");

            exp2.genExpressionOutput(stringBuilder, fullNamespaces, context);

            if(exp2.isComplex())
                stringBuilder.append(")");
        }
        else
            stringBuilder.append("?");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionComparation))
            return false;

        QGConditionComparation o = (QGConditionComparation) obj;

        return  type == o.type
                && exp1.equals(o.exp1)
                && Objects.equals(exp2, o.exp2);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = null;

        if(exp2 != null)
            result = exp2.getAutoFields();

        return QGQueryBase.joinLists(result, exp1.getAutoFields());
    }


    enum ComparationType
    {
        GREATER(">"),
        GREATER_EQUAL(">="),
        EQUALS("="),
        LESS_EQUAL("<="),
        LESS("<"),
        LIKE("LIKE");

        final String symbol;

        ComparationType(String symbol)
        {
            this.symbol = symbol;
        }
    }
}
