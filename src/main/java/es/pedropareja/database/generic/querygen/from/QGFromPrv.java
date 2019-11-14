package es.pedropareja.database.generic.querygen.from;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGFromPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGFrom, QGLinkOptionalPrv<QGFrom>
{
    private final Class<T> tableType;

    QGFromPrv(Class<T> tableType, QGQueryInit init)
    {
        super(init);
        this.tableType = tableType;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" FROM ");

        printTablePath(stringBuilder, tableType, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGFrom getThis()
    {
        return this;
    }
}
