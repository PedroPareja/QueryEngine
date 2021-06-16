package com.github.pedropareja.database.generic.querygen.where;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

import java.util.ArrayList;
import java.util.List;

public class QGWherePrv extends QGQueryMiddleEnd
        implements QGWhere, QGLinkConditionsPrv<QGWhere>, QGAutoFields
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
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        if(!conditionList.isEmpty())
        {
            stringBuilder.append(" WHERE");

            for (int i = 0; i < conditionList.size(); i++)
            {
                conditionList.get(i).genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), context);
                if (i < conditionList.size() - 1)
                    stringBuilder.append(" AND");
            }
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = new ArrayList<>();

        for(QGConditionBase condition: conditionList)
            result = QGQueryBase.joinLists(result, condition.getAutoFields());

        return result;
    }
}
