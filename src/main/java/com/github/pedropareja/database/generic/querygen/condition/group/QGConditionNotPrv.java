package com.github.pedropareja.database.generic.querygen.condition.group;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;

import java.util.ArrayList;
import java.util.List;

public class QGConditionNotPrv<T extends QGOptionalityEnabled & QGInitReferenced & QGLinkConditions<T>>
        implements QGConditionNot<T>, QGLinkConditionsPrv<T>
{
    private final QGQueryInit init;
    private final T parent;
    private List<QGConditionBase> conditionList = new ArrayList<>();

    public QGConditionNotPrv(QGQueryInit init, T parent)
    {
        this.init = init;
        this.parent = parent;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        if(!isNull())
        {
            stringBuilder.append(" NOT(");
            conditionList.get(0).genExpressionOutput(stringBuilder, fullNamespaces, query, context);
            stringBuilder.append(")");
        }
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return !conditionList.isEmpty() ? conditionList.get(0).getAutoFields() : new ArrayList<>();
    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return conditionList;
    }

    @Override
    public T getThis()
    {
        return parent;
    }

    @Override
    public QGQueryInit getInit()
    {
        return init;
    }

    @Override
    public boolean getNextOptionalAppearanceValueAndReset()
    {
        return true;
    }

    @Override
    public void setNextOptionalAppearanceValue(boolean nextOptionalAppearanceValue) {}

    @Override
    public boolean isNull()
    {
        return conditionList.isEmpty() || conditionList.get(0).isNull();
    }
}
