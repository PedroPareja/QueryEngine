package es.pedropareja.database.generic.querygen.delete;

import es.pedropareja.database.generic.DBTable;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGDeletePrv extends QGQueryInit implements QGDelete
{
    private final DBTable table;

    public QGDeletePrv(DBTable table)
    {
        this.table = table;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("DELETE FROM ");
        printTablePath(stringBuilder, table, context);

        genOutputNext(stringBuilder, context);
    }
}
