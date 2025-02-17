package com.github.pedropareja.database.generic.querygen.expression.coalesce;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;

import java.util.List;

public class QGCoalescePrv extends QGExpressionBase
        implements QGCoalesce, QGLinkAsPrv, QGLinkOperatorsPrv
{
    private final QGExpression[] parameters;

    public QGCoalescePrv(QGExpressionPrv init, QGExpression[] parameters)
    {
        super(init);
        this.parameters = parameters;
    }

    public QGCoalescePrv(QGExpression[] parameters)
    {
        super();
        this.parameters = parameters;
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        List<DBFieldInfo> result = null;

        for(QGExpression expression: parameters)
            result = QGQueryBase.joinLists(result, expression.getAutoFields());

        return result;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append("COALESCE(");

        for(int i=0; i < parameters.length; i++)
        {
            stringBuilder.append(i != 0 ? ", " : "");
            parameters[i].genExpressionOutput(stringBuilder, fullNamespaces, context);
        }

        stringBuilder.append(")");
    }
}
