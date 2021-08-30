package com.github.pedropareja.database.generic.querygen.expression.subquery;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;

public class QGSubQueryPrv extends QGExpressionBase
    implements QGSubQuery, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private final QGQuery query;

    public QGSubQueryPrv(QGExpressionPrv init, QGQuery query)
    {
        super(init);
        this.query = query;
    }

    public QGSubQueryPrv(QGQuery query)
    {
        super();
        this.query = query;
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return query.getAutoFields();
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append("(");
        query.genExpressionOutput(stringBuilder, fullNamespaces, query, context);
        stringBuilder.append(")");
    }
}
