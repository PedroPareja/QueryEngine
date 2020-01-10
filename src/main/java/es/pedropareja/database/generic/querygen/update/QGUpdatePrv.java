package es.pedropareja.database.generic.querygen.update;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGUpdatePrv<T extends Enum<?> & DBFieldInfo> extends QGQueryInit implements QGUpdate
{
    private final Class<T> tableType;

    public QGUpdatePrv(Class<T> tableType)
    {
        this.tableType = tableType;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("UPDATE ");
        printTablePath(stringBuilder, tableType, context);

        genOutputNext(stringBuilder, context);
    }
}
