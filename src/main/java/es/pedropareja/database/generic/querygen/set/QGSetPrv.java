package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGSetPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGSet
{
    private final T[] fieldList;

    @SafeVarargs
    public QGSetPrv(QGQueryInit init, T ... fieldList)
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

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGSetPrv))
            return false;

        return fieldArrayEquals(fieldList, ((QGSetPrv<?>)q).fieldList);
    }
}
