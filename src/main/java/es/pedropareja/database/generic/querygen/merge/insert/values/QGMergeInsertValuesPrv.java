package es.pedropareja.database.generic.querygen.merge.insert.values;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public class QGMergeInsertValuesPrv extends QGQueryMiddleEnd implements QGMergeInsertValues
{
    private final QGExpression[] expressions;

    @SafeVarargs
    public QGMergeInsertValuesPrv(QGQueryInit init, QGExpression ... expressions)
    {
        super(init);
        this.expressions = expressions;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" VALUES(");

        for(int i=0; i < expressions.length; i++)
        {
            if(i != 0)
                stringBuilder.append(", ");

            expressions[i].genExpressionOutput(stringBuilder, true, context);
        }

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
