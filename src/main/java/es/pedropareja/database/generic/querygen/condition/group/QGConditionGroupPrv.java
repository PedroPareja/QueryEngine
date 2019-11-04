package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.util.List;

public class QGConditionGroupPrv<U extends QGConditionGroup<U,T>, T extends QGOptionalityEnabled & QGInitReferenced>
    implements QGConditionGroup<U,T>, QGLinkConditionsPrv<U>
{

    @Override
    public QGQueryInit getInit()
    {
        return null;
    }

    @Override
    public boolean getNextOptionalAppearanceValueAndReset()
    {
        return false;
    }

    @Override
    public void setNextOptionalAppearanceValue(boolean nextOptionalAppearanceValue)
    {

    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return null;
    }

    @Override
    public U getThis()
    {
        return null;
    }
}
