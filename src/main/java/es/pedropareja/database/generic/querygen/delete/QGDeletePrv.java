package es.pedropareja.database.generic.querygen.delete;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGDeletePrv<T extends Enum<?> & DBFieldInfo> extends QGQueryInit implements QGDelete
{
    private final Class<T> tableType;

    public QGDeletePrv(Class<T> tableType)
    {
        this.tableType = tableType;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("DELETE FROM ");
        printTablePath(stringBuilder, tableType, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGDeletePrv))
            return false;

        return tableType.equals(((QGDeletePrv)q).tableType);
    }
}
