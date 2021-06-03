package es.pedropareja.database.generic.querygen.with.then;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGWithThenPrv extends QGQueryMiddleEnd
    implements QGWithThen
{
    private final QGQuery mainQuery;

    public QGWithThenPrv(QGQuery mainQuery, QGQueryInit init)
    {
        super(init);
        this.mainQuery = mainQuery;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" ");
        mainQuery.genExpressionOutput(stringBuilder, true, context);
    }
}
