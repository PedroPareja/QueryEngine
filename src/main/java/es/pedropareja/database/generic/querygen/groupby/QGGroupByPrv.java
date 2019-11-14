package es.pedropareja.database.generic.querygen.groupby;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.auto.QGAutoFields;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Arrays;
import java.util.List;

public class QGGroupByPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd
        implements QGGroupBy, QGLinkOptionalPrv<QGGroupBy>, QGAutoFields
{
    private T[] fieldList;

    public QGGroupByPrv(QGQueryInit init, T[] fieldList)
    {
        super(init);
        this.fieldList = fieldList;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(fieldList);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" GROUP BY");

        for(int i=0; i < fieldList.length; i++)
        {
            stringBuilder.append(i == 0 ? " " : ", ");
            printField(stringBuilder, fieldList[i], getInit().isFullNamespaces(), context);
        }
    }

    @Override
    public QGGroupBy getThis()
    {
        return this;
    }
}
