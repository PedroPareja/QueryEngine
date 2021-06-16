package com.github.pedropareja.database.generic.querygen.offset;

import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGOffsetPrv extends QGQueryMiddleEnd implements QGOffset
{
    private final Integer offset;

    public QGOffsetPrv(int offset, QGQueryInit init)
    {
        super(init);
        this.offset = offset;
    }

    public QGOffsetPrv(QGQueryInit init)
    {
        super(init);
        this.offset = null;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" OFFSET ").append(offset != null ? offset : "?");

        genOutputNext(stringBuilder, context);
    }
}
