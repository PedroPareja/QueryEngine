package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

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
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(!isNull())
        {
            stringBuilder.append(" NOT(");
            conditionList.get(0).genOutput(stringBuilder, fullNamespaces, context);
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
