package es.pedropareja.database.generic.querygen.on;

import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.util.ArrayList;
import java.util.List;

public class QGOnPrv extends QGQueryMiddleEnd implements QGOn, QGLinkConditionsPrv<QGOn>
{
    private List<QGConditionBase> conditionList = new ArrayList<>();

    public QGOnPrv(QGQueryInit init)
    {
        super(init);
    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return conditionList;
    }

    @Override
    public QGOn getThis()
    {
        return this;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        if(!conditionList.isEmpty())
            stringBuilder.append(" ON");

        for(int i=0; i < conditionList.size(); i++)
        {
            if(i != 0)
                stringBuilder.append(" AND");

            conditionList.get(i).genOutput(stringBuilder, true, context);
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGOnPrv))
            return false;

        return conditionListEquals(conditionList, ((QGOnPrv)q).conditionList);
    }
}
