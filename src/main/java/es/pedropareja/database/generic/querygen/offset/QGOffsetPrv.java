package es.pedropareja.database.generic.querygen.offset;

import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

import java.util.Objects;

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
