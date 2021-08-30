package com.github.pedropareja.database.generic.querygen.condition;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.DBFieldInfo;

import java.util.List;

public class QGConditionIsNull implements QGConditionBase
{
    private final QGExpression exp;

    public QGConditionIsNull(QGExpression exp)
    {
        this.exp = exp;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        stringBuilder.append(" ");

        if(exp.isComplex())
            stringBuilder.append("(");

        exp.genExpressionOutput(stringBuilder, fullNamespaces, query, context);

        if(exp.isComplex())
            stringBuilder.append(")");

        stringBuilder.append(" IS NULL");
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return exp.getAutoFields();
    }
}
