package com.github.pedropareja.database.generic.querygen.expression.function;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;

public class QGFunctionPrv extends QGExpressionBase
    implements QGFunction, QGLinkAsPrv, QGLinkOperatorsPrv
{
    private final String functionName;
    private final QGExpression[] parameters;

    public QGFunctionPrv(QGExpressionPrv init, String functionName, QGExpression[] parameters)
    {
        super(init);
        this.functionName = functionName;
        this.parameters = parameters;
    }

    public QGFunctionPrv(String functionName, QGExpression[] parameters)
    {
        super();
        this.functionName = functionName;
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
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        stringBuilder.append(functionName).append("(");

        for(int i=0; i < parameters.length; i++)
        {
            stringBuilder.append(i != 0 ? ", " : "");
            parameters[i].genExpressionOutput(stringBuilder, fullNamespaces, query, context);
        }

        stringBuilder.append(")");
    }
}
