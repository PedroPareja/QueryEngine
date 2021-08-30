package com.github.pedropareja.database.generic.querygen.expression.casecmd.when;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGCase;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGCasePrv;

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
        List<DBFieldInfo> result = null;

        for(QGConditionBase condition: conditionList)
            result = QGQueryBase.joinLists(result, condition.getAutoFields());

        if(thenExp != null)
            result = QGQueryBase.joinLists(result, thenExp.getAutoFields());

        return result;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        if(!conditionList.isEmpty())
        {
            stringBuilder.append(" WHEN");

            for(int i=0; i < conditionList.size(); i++)
            {
                conditionList.get(i).genExpressionOutput(stringBuilder, fullNamespaces, query, context);
                if (i < conditionList.size() - 1)
                    stringBuilder.append(" AND");
            }

            stringBuilder.append(" THEN ");
            thenExp.genExpressionOutput(stringBuilder, fullNamespaces, query, context);
        }
    }

    @Override
    public boolean isComplex()
    {
        return true;
    }
}
