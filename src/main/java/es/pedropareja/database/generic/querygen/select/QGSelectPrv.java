package es.pedropareja.database.generic.querygen.select;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGSelectPrv extends QGQueryInit implements QGSelect
{
    private final DBFieldInfo[] fieldList;
    private boolean distinct = false;

    @SafeVarargs
    public QGSelectPrv(DBFieldInfo ... fieldList)
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

    public DBFieldInfo[] getFieldList()
    {
        return fieldList;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("SELECT").append(distinct ? " DISTINCT " : " ");

        if(fieldList == null || fieldList.length == 0)
            stringBuilder.append("*");
        else
        {
            for (int i = 0; i < fieldList.length; i++)
            {
                stringBuilder.append(i != 0 ? ", " : "");
                printField(stringBuilder, fieldList[i], fullNamespaces, context);
            }
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGSelectPrv))
            return false;

        QGSelectPrv qSelect = (QGSelectPrv) q;

        return fieldArrayEquals(fieldList, qSelect.fieldList) && distinct == qSelect.distinct;
    }
}
