package es.pedropareja.database.generic.querygen.into;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.insert.QGInsertPrv;

public class QGIntoPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGInto
{
    private final Class<T> tableType;

    public QGIntoPrv(Class<T> tableType, QGQueryInit init)
    {
        super(init);
        this.tableType = tableType;
    }

    @Override
    public void genOutput(StringBuilder stringBuilder)
    {
        stringBuilder.append(" INTO ");
        printTablePath(stringBuilder, tableType);
        stringBuilder.append(" VALUES (");

        int numParams = ((QGInsertPrv<?>)getInit()).getFieldList().length;
        for(int i=0; i < numParams; i++)
            stringBuilder.append(i != 0 ? ", " : "").append("?");

        stringBuilder.append(")");
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGIntoPrv))
            return false;

        return tableType.equals(((QGIntoPrv<?>)q).tableType);
    }
}
