package es.pedropareja.database.generic.querygen.expression.casecmd.when;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.casecmd.QGCase;
import es.pedropareja.database.generic.querygen.expression.casecmd.QGCasePrv;

import java.util.ArrayList;
import java.util.List;

public class QGWhenPrv implements QGWhen, QGLinkConditionsPrv<QGWhen>
{
    private QGCasePrv caseParent;
    private QGExpression thenExp;
    private List<QGConditionBase> conditionList = new ArrayList<>();
    private boolean nextOptionalAppearance = true;

    public QGWhenPrv(QGCasePrv caseParent)
    {
        this.caseParent = caseParent;
    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return conditionList;
    }

    @Override
    public QGWhen getThis()
    {
        return this;
    }

    @Override
    public QGCase then(QGExpression exp)
    {
        this.thenExp = exp;
        return caseParent;
    }

    @Override
    public QGQueryInit getInit()
    {
        return null;
    }

    @Override
    public boolean getNextOptionalAppearanceValueAndReset()
    {
        boolean result = nextOptionalAppearance;
        nextOptionalAppearance = true;
        return result;
    }

    @Override
    public void setNextOptionalAppearanceValue(boolean nextOptionalAppearanceValue)
    {
        this.nextOptionalAppearance = nextOptionalAppearanceValue;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return thenExp != null ? thenExp.getAutoFields() : null;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(!conditionList.isEmpty())
        {
            stringBuilder.append(" WHEN");

            for(int i=0; i < conditionList.size(); i++)
            {
                conditionList.get(i).genOutput(stringBuilder, fullNamespaces, context);
                if (i < conditionList.size() - 1)
                    stringBuilder.append(" AND");
            }

            stringBuilder.append(" THEN ");
            thenExp.genExpressionOutput(stringBuilder, fullNamespaces, context);
        }
    }
}
