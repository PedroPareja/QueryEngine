package com.github.pedropareja.database.generic.querygen.condition.group;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.util.ArrayList;
import java.util.List;

public abstract class QGConditionGroupPrv<U extends QGConditionGroup<U,T>, T extends QGOptionalityEnabled & QGInitReferenced>
    implements QGConditionGroup<U,T>, QGLinkConditionsPrv<U>
{
    protected final QGQueryInit init;
    protected final T parent;
    protected boolean nextOptionalAppearanceValue = true;
    protected List<QGConditionBase> conditionList = new ArrayList<>();

    public QGConditionGroupPrv(QGQueryInit init, T parent)
    {
        this.init = init;
        this.parent = parent;
    }

    @Override
    public QGQueryInit getInit()
    {
        return init;
    }

    @Override
    public boolean getNextOptionalAppearanceValueAndReset()
    {
        boolean result = nextOptionalAppearanceValue;
        nextOptionalAppearanceValue = true;
        return result;
    }

    @Override
    public void setNextOptionalAppearanceValue(boolean nextOptionalAppearanceValue)
    {
        this.nextOptionalAppearanceValue = nextOptionalAppearanceValue;
    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return conditionList;
    }

    @Override
    public U getThis()
    {
        return (U)this;
    }

    @Override
    public T end()
    {
        return parent;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = new ArrayList<>();

        for(QGConditionBase condition: conditionList)
        {
            List<DBFieldInfo> conditionAutofields = condition.getAutoFields();

            if(conditionAutofields != null)
                result.addAll(condition.getAutoFields());
        }

        return result;
    }

    @Override
    public boolean isNull()
    {
        if(conditionList.isEmpty())
            return true;

        for(QGConditionBase condition: conditionList)
            if(!condition.isNull())
                return false;

        return true;
    }

    protected static <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context, List<QGConditionBase> conditionList, String nexusOperator)
    {
        boolean conditionWritten = false;

        stringBuilder.append(" (");

        for (QGConditionBase condition: conditionList)
        {
            if(!condition.isNull())
            {
                if(conditionWritten)
                    stringBuilder.append(" ").append(nexusOperator);

                condition.genExpressionOutput(stringBuilder, fullNamespaces, query, context);
                conditionWritten = true;
            }
        }

        stringBuilder.append(")");
    }
}
