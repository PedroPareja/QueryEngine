package es.pedropareja.database.generic.querygen.merge.when;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGMergeWhenPrv extends QGQueryMiddleEnd implements QGMergeWhen
{
    private final QGMergeWhenType type;

    public QGMergeWhenPrv(QGQueryInit init, QGMergeWhenType type)
    {
        super(init);
        this.type = type;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" WHEN ");
        stringBuilder.append(type.getSqlText());

        genOutputNext(stringBuilder, context);
    }
}
