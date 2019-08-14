package es.pedropareja.database.generic.querygen.select;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGSelectPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryInit implements QGSelect
{
    private final T[] fieldList;
    private boolean distinct = false;

    @SafeVarargs
    public QGSelectPrv(T ... fieldList)
    {
        this.fieldList = fieldList;
    }

    public QGSelectPrv() { this.fieldList = null; }

    @Override
    public QGSelect distinct()
    {
        distinct = true;
        return this;
    }

    @Override
    public void genOutput(StringBuilder stringBuilder)
    {
        stringBuilder.append("SELECT").append(distinct ? " DISTINCT " : " ");

        if(fieldList == null || fieldList.length == 0)
            stringBuilder.append("*");
        else
        {
            for (int i = 0; i < fieldList.length; i++)
            {
                stringBuilder.append(i != 0 ? ", " : "");
                printField(stringBuilder, fieldList[i], fullNamespaces);
            }
        }

        genOutputNext(stringBuilder);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGSelectPrv))
            return false;

        QGSelectPrv<?> qSelect = (QGSelectPrv<?>) q;

        return fieldArrayEquals(fieldList, qSelect.fieldList) && distinct == qSelect.distinct;
    }
}
