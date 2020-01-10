package es.pedropareja.database.generic.querygen.limit;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGLimitPrv extends QGQueryMiddleEnd implements QGLimit, QGLinkOptionalPrv<QGLimit>
{
    private final Integer rowLimit;

    public QGLimitPrv(int rowLimit, QGQueryInit init)
    {
        super(init);
        this.rowLimit = rowLimit;
    }

    public QGLimitPrv(QGQueryInit init)
    {
        super(init);
        this.rowLimit = null;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" LIMIT ").append(rowLimit != null ? rowLimit : "?");

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGLimit getThis()
    {
        return this;
    }
}
