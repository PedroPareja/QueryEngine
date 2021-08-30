package com.github.pedropareja.database.generic.querygen.on;

import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

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

            conditionList.get(i).genExpressionOutput(stringBuilder, true, this, context);
        }

        genOutputNext(stringBuilder, context);
    }
}
