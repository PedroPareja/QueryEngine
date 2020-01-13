package es.pedropareja.database.generic.querygen.merge;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGMergePrv<T extends Enum<?> & DBFieldInfo> extends QGQueryInit implements QGMerge
{
    private final Class<T> tableType;

    public QGMergePrv(Class<T> tableType)
    {
        this.tableType = tableType;
        setFullNamespaces();
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append("MERGE INTO ");
        printTablePath(stringBuilder, tableType, context);

        genOutputNext(stringBuilder, context);
    }
}
