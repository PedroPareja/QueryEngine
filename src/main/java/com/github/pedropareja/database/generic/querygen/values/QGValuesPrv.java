package com.github.pedropareja.database.generic.querygen.values;

import com.github.pedropareja.database.generic.exceptions.QueryGenException;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QGValuesPrv extends QGQueryMiddleEnd implements QGValues
{
    private final List<QGExpression> values;
    private final QGQuery select;

    @SafeVarargs
    public QGValuesPrv(QGQueryInit init, QGExpression ... values)
    {
        super(init);
        this.values = new ArrayList<>(Arrays.asList(values));
        this.select = null;
    }

    public QGValuesPrv(QGQueryInit init, QGQuery select)
    {
        super(init);
        this.values = null;
        this.select = select;
    }

    @Override
    public QGValues and(QGExpression... values)
    {
        if(this.values == null)
            throw new QueryGenException("Can not use 'and' in a not value based insert");

        this.values.addAll(Arrays.asList(values));
        return this;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        if(this.values != null)
        {
            stringBuilder.append(" VALUES (");

            for (int i = 0; i < values.size(); i++)
            {
                if (i != 0)
                    stringBuilder.append(", ");

                values.get(i).genExpressionOutput(stringBuilder, true, this, context);
            }

            stringBuilder.append(")");
        }
        else
        {
            stringBuilder.append(" ");
            this.select.getInit().setFullNamespaces();
            this.select.getInit().genOutput(stringBuilder, context);
        }

        genOutputNext(stringBuilder, context);
    }
}
