package es.pedropareja.database.generic.querygen.join;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGJoinPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGJoin, QGLinkOptionalPrv<QGJoin>
{
    private final Class<T> tableType;

    public QGJoinPrv(Class<T> tableType, QGQueryInit init)
    {
        super(init);
        this.tableType = tableType;
        init.setFullNamespaces();
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" JOIN ");

        printTablePath(stringBuilder, tableType, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGJoinPrv))
            return false;

        return tableType.equals(((QGJoinPrv<?>)q).tableType);
    }

    @Override
    public QGJoin getThis()
    {
        return this;
    }
}
