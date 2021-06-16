package com.github.pedropareja.database.generic.querygen.join;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGJoinPrv extends QGQueryMiddleEnd implements QGJoin, QGLinkOptionalPrv<QGJoin>
{
    private final QGExpression tableExp;
    private final JoinType joinType;

    public QGJoinPrv(QGExpression tableExp, QGQueryInit init, JoinType joinType)
    {
        super(init);
        this.tableExp = tableExp;
        this.joinType = joinType;
        init.setFullNamespaces();
    }

    public QGJoinPrv(QGExpression tableExp, QGQueryInit init)
    {
        this(tableExp, init, JoinType.INNER);
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

        tableExp.genExpressionOutput(stringBuilder, true, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGJoin getThis()
    {
        return this;
    }
}
