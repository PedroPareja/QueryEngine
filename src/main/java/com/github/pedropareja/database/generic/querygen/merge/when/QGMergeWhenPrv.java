package com.github.pedropareja.database.generic.querygen.merge.when;

import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;

import java.util.ArrayList;
import java.util.List;

public class QGMergeWhenPrv extends QGQueryMiddleEnd implements QGMergeWhen
{
    private final QGMergeWhenType type;
    private final List<QGConditionBase> conditionList = new ArrayList<>();

    public QGMergeWhenPrv(QGQueryInit init, QGMergeWhenType type)
    {
        super(init);
        this.type = type;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" WHEN ");
        stringBuilder.append(type.getSqlText());

        for (int i = 0; i < conditionList.size(); i++)
        {
            stringBuilder.append(" AND");
            conditionList.get(i).genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), context);
        }

        stringBuilder.append(" THEN ");

        genOutputNext(stringBuilder, context);
    }
}
