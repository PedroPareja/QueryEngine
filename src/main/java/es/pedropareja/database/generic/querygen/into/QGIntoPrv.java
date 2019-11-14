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
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" VALUES (");

        int numParams = ((QGInsertPrv)getInit()).getFieldList().length;
        for(int i=0; i < numParams; i++)
            stringBuilder.append(i != 0 ? ", " : "").append("?");

        stringBuilder.append(")");
    }

    public Class<T> getTableType()
    {
        return tableType;
    }
}
