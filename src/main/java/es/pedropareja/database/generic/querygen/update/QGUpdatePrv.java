package es.pedropareja.database.generic.querygen.update;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGUpdatePrv<T extends Enum<?> & DBFieldInfo> extends QGQueryInit implements QGUpdate
{
    private final Class<T> tableType;

    public QGUpdatePrv(Class<T> tableType)
    {
        this.tableType = tableType;
    }

    @Override
    public void genOutput(StringBuilder stringBuilder)
    {
        stringBuilder.append("UPDATE ");
        printTablePath(stringBuilder, tableType);

        genOutputNext(stringBuilder);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGUpdatePrv))
            return false;

        return tableType.equals(((QGUpdatePrv<?>)q).tableType);
    }
}
