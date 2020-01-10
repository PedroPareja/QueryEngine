package es.pedropareja.database.generic.querygen.join;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGJoinPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGJoin, QGLinkOptionalPrv<QGJoin>
{
    private final Class<T> tableType;
    private final JoinType joinType;

    public QGJoinPrv(Class<T> tableType, QGQueryInit init, JoinType joinType)
    {
        super(init);
        this.tableType = tableType;
        this.joinType = joinType;
        init.setFullNamespaces();
    }

    public QGJoinPrv(Class<T> tableType, QGQueryInit init)
    {
        this(tableType, init, JoinType.INNER);
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        switch (joinType)
        {
            case LEFT:
                stringBuilder.append(" LEFT");
                break;

            case RIGHT:
                stringBuilder.append(" RIGHT");
                break;

            case FULL:
                stringBuilder.append(" FULL");
                break;
        }

        stringBuilder.append(" JOIN ");

        printTablePath(stringBuilder, tableType, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGJoin getThis()
    {
        return this;
    }
}
