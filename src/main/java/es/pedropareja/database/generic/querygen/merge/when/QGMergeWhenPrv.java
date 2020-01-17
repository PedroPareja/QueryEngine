package es.pedropareja.database.generic.querygen.merge.when;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGMergeWhenPrv extends QGQueryMiddleEnd implements QGMergeWhen
{
    public QGMergeWhenPrv(QGQueryInit init)
    {
        super(init);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" WHEN");

        genOutputNext(stringBuilder, context);
    }
}
