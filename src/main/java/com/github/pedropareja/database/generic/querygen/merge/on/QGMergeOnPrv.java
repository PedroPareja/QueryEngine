package com.github.pedropareja.database.generic.querygen.merge.on;

import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.util.ArrayList;
import java.util.List;

public class QGMergeOnPrv extends QGQueryMiddleEnd implements QGMergeOn, QGLinkConditionsPrv<QGMergeOn>
{
    private List<QGConditionBase> conditionList = new ArrayList<>();

    public QGMergeOnPrv(QGQueryInit init)
    {
        super(init);
    }

    @Override
    public List<QGConditionBase> getConditionList()
    {
        return conditionList;
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

            conditionList.get(i).genExpressionOutput(stringBuilder, true, context);
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGMergeOn getThis()
    {
        return this;
    }
}
