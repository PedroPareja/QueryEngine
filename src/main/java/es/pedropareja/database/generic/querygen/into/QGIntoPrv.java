package es.pedropareja.database.generic.querygen.into;

import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.insert.QGInsertPrv;

public class QGIntoPrv extends QGQueryMiddleEnd implements QGInto
{
    private final DBTable table;

    public QGIntoPrv(DBTable table, QGQueryInit init)
    {
        super(init);
        this.table = table;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        if(next == null)
        {
            stringBuilder.append(" VALUES (");

            int numParams = ((QGInsertPrv) getInit()).getFieldList().length;
            for (int i = 0; i < numParams; i++)
                stringBuilder.append(i != 0 ? ", " : "").append("?");

            stringBuilder.append(")");
        }

        genOutputNext(stringBuilder, context);
    }

    public DBTable getTable()
    {
        return table;
    }
}
