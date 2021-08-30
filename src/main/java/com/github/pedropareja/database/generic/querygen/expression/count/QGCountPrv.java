package com.github.pedropareja.database.generic.querygen.expression.count;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QGCountPrv extends QGExpressionBase implements QGCount, QGLinkAsPrv, QGLinkOperatorsPrv
{
    private final List<QGExpression> parameters;
    private boolean distinct;

    public QGCountPrv()
    {
        this.parameters = null;
        this.distinct = false;
    }

    public QGCountPrv(QGExpressionPrv init)
    {
        super(init);
        this.parameters = null;
        this.distinct = false;
    }

    public QGCountPrv(QGExpression ... expressions)
    {
        this.parameters = new ArrayList<>(Arrays.asList(expressions));
        this.distinct = false;
    }

    public QGCountPrv(QGExpressionPrv init, QGExpression ... expressions)
    {
        super(init);
        this.parameters = new ArrayList<>(Arrays.asList(expressions));
        this.distinct = false;
    }

    @Override
    public QGCount distinct()
    {
        distinct = true;
        return this;
    }

    @Override
    public QGCount and(QGExpression... expressions)
    {
        parameters.addAll(Arrays.asList(expressions));
        return this;
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        List<DBFieldInfo> result = null;

        if(parameters != null)
            for(QGExpression expression: parameters)
                result = QGQueryBase.joinLists(result, expression.getAutoFields());

        return result;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append("COUNT(");

        if(distinct)
            stringBuilder.append("DISTINCT ");

        if(parameters == null || parameters.isEmpty())
            stringBuilder.append("*");
        else
            for(int i=0; i < parameters.size(); i++)
            {
                if(i!=0)
                    stringBuilder.append(", ");

                parameters.get(i).genExpressionOutput(stringBuilder, fullNamespaces, query, context);
            }

        stringBuilder.append(")");
    }
}
