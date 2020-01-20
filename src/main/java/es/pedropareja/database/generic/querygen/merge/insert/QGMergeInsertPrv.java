package es.pedropareja.database.generic.querygen.merge.insert;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

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

            fields[i].genExpressionOutput(stringBuilder, true, context);
        }

        stringBuilder.append(")");

        genOutputNext(stringBuilder, context);
    }
}
