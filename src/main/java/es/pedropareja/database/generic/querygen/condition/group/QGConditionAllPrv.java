package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.util.List;

public class QGConditionAllPrv<T extends QGOptionalityEnabled & QGInitReferenced>
    implements QGConditionAll<T>, QGLinkConditionsPrv<QGConditionAll<T>>
{

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
    public QGConditionAll<T> getThis()
    {
        return null;
    }

    @Override
    public T end()
    {
        return null;
    }

    @Override
    public QGQueryInit getInit()
    {
        return null;
    }
}
