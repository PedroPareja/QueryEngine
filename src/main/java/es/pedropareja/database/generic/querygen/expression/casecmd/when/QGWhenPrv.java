package es.pedropareja.database.generic.querygen.expression.casecmd.when;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.casecmd.QGCase;
import es.pedropareja.database.generic.querygen.expression.casecmd.QGCasePrv;

import java.util.List;

public class QGWhenPrv implements QGWhen, QGLinkConditionsPrv<QGWhen>
{
    private QGCasePrv caseParent;

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return null;
    }

    @Override
    public QGWhen getThis()
    {
        return this;
    }

    @Override
    public QGCase then(QGExpression exp)
    {
        return null;
    }

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
}
