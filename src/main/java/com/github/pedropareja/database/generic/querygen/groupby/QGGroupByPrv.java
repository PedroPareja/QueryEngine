package com.github.pedropareja.database.generic.querygen.groupby;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

import java.util.List;

public class QGGroupByPrv extends QGQueryMiddleEnd
        implements QGGroupBy, QGLinkOptionalPrv<QGGroupBy>, QGAutoFields
{
    private QGExpression[] expList;

    public QGGroupByPrv(QGQueryInit init, QGExpression[] expList)
    {
        super(init);
        this.expList = expList;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = null;

        for(QGExpression exp: expList)
            result = joinLists(result, exp.getAutoFields());

        return result;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" GROUP BY");

        for(int i=0; i < expList.length; i++)
        {
            stringBuilder.append(i == 0 ? " " : ", ");
            expList[i].genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), this, context);
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGGroupBy getThis()
    {
        return this;
    }
}
