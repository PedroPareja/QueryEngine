package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGSetPrv extends QGQueryMiddleEnd implements QGSet
{
    private final DBFieldInfo[] fieldList;

    @SafeVarargs
    public QGSetPrv(QGQueryInit init, DBFieldInfo ... fieldList)
    {
        super(init);
        this.fieldList = fieldList;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" SET ");

        for(int i=0; i < fieldList.length; i++)
        {
            stringBuilder.append(i != 0 ? ", " : "");
            printField(stringBuilder, fieldList[i], getInit().isFullNamespaces(), context);
            stringBuilder.append(" = ?");
        }

        genOutputNext(stringBuilder, context);
    }
}
