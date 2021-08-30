package com.github.pedropareja.database.generic.querygen.with;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGWithPrv extends QGQueryInit
    implements QGWith
{
    private String id;
    private final QGExpression[] fields;
    private boolean recursive = false;


    public QGWithPrv(QGExpression[] fields)
    {
        this.fields = fields;
    }

    @Override
    public QGWith recursive()
    {
        recursive = true;
        return this;
    }

    @Override
    public QGWith id(String id)
    {
        this.id = id;
        return this;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append("WITH").append(recursive ? " RECURSIVE " : " ").append(id);

        stringBuilder.append(" (");

        for(int i=0; i < fields.length; i++)
        {
            stringBuilder.append(i != 0 ? ", " : "");
            fields[i].genExpressionOutput(stringBuilder, true, this, context);
        }

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
