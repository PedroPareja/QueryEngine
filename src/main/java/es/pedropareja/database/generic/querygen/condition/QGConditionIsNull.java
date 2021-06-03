package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.List;

public class QGConditionIsNull implements QGConditionBase
{
    private final QGExpression exp;

    public QGConditionIsNull(QGExpression exp)
    {
        this.exp = exp;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");

        if(exp.isComplex())
            stringBuilder.append("(");

        exp.genExpressionOutput(stringBuilder, fullNamespaces, context);

        if(exp.isComplex())
            stringBuilder.append(")");

        stringBuilder.append(" IS NULL");
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return exp.getAutoFields();
    }
}
