package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

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
            result.addAll(condition.getAutoFields());

        return result;
    }
}
