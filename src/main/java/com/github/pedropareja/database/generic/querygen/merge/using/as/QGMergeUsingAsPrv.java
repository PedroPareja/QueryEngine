package com.github.pedropareja.database.generic.querygen.merge.using.as;

import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGMergeUsingAsPrv extends QGQueryMiddleEnd implements QGMergeUsingAs
{
    private final String id;

    public QGMergeUsingAsPrv(QGQueryInit init, String id)
    {
        super(init);
        this.id = id;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" AS ").append(id);

        genOutputNext(stringBuilder, context);
    }
}
