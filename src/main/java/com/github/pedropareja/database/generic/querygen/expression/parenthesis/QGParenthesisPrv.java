package com.github.pedropareja.database.generic.querygen.expression.parenthesis;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;

public class QGParenthesisPrv extends QGExpressionBase
    implements QGParenthesis, QGLinkAsPrv, QGLinkOperatorsPrv
{
    private final QGExpression expression;

    public QGParenthesisPrv(QGExpressionPrv init, QGExpression expression)
    {
        super(init);
        this.expression = expression;
    }

    public QGParenthesisPrv(QGExpression expression)
    {
        super();
        this.expression = expression;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append("(");
        expression.genExpressionOutput(stringBuilder, fullNamespaces, context);
        stringBuilder.append(")");
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return expression.getAutoFields();
    }
}
