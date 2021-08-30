package com.github.pedropareja.database.generic.querygen.merge.insert;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGMergeInsertPrv extends QGQueryMiddleEnd implements QGMergeInsert
{
    private final DBFieldInfo[] fields;

    @SafeVarargs
    public QGMergeInsertPrv(QGQueryInit init, DBFieldInfo ... fields)
    {
        super(init);
        this.fields = fields;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" INSERT(");

        for(int i=0; i < fields.length; i++)
        {
            if(i != 0)
                stringBuilder.append(", ");

            fields[i].genExpressionOutput(stringBuilder, false, this, context);
        }

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
