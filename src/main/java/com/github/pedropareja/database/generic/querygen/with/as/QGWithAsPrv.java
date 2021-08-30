package com.github.pedropareja.database.generic.querygen.with.as;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGWithAsPrv extends QGQueryMiddleEnd
    implements QGWithAs
{

    private final QGQuery anchorClause;
    private QGQuery recursiveClause;

    public QGWithAsPrv(QGQuery anchorClause, QGQueryInit init)
    {
        super(init);
        this.anchorClause = anchorClause;
    }

    @Override
    public QGWithAs unionAll(QGQuery recursiveClause)
    {
        this.recursiveClause = recursiveClause;
        return this;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" AS (");
        anchorClause.genExpressionOutput(stringBuilder, true, this, context);

        if(recursiveClause != null)
        {
            stringBuilder.append(" UNION ALL ");
            recursiveClause.genExpressionOutput(stringBuilder, true, this, context);
        }

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
