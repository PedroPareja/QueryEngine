package es.pedropareja.database.generic.querygen.where;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QGWherePrv extends QGQueryMiddleEnd implements QGWhere, QGLinkConditionsPrv<QGWhere, QGWherePrv>
{
    private List<QGConditionBase> conditionList = new ArrayList<>();

    public QGWherePrv(QGQueryInit init)
    {
        super(init);
    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return conditionList;
    }

    @Override
    public QGWherePrv getThis()
    {
        return this;
    }

    @Override
    public QGWherePrv getPrv()
    {
        return this;
    }


    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        if(!conditionList.isEmpty())
        {
            stringBuilder.append(" WHERE");

            for (int i = 0; i < conditionList.size(); i++)
            {
                conditionList.get(i).genOutput(stringBuilder, getInit().isFullNamespaces(), context);
                if (i < conditionList.size() - 1)
                    stringBuilder.append(" AND");
            }
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGWherePrv))
            return false;

        return conditionListEquals(conditionList, ((QGWherePrv)q).conditionList);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static <T extends Enum<?> & DBFieldInfo, U, F extends Enum<?> & DBFilterProcessor<U>> void applyFilter(QGLinkConditions<?> conditionLink, Class<F> filterProcessorType, U filter) throws SQLException
    {
        for(F filterRule: filterProcessorType.getEnumConstants())
            filterRule.getQueryCondition().doAction(conditionLink, filter);
    }
}
