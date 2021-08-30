package com.github.pedropareja.database.generic.querygen.condition.group;

import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGConditionAllPrv<T extends QGOptionalityEnabled & QGInitReferenced>
    extends QGConditionGroupPrv<QGConditionAll<T>,T>
    implements QGConditionAll<T>
{
    public QGConditionAllPrv(QGQueryInit init, T parent)
    {
        super(init, parent);
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        if(!isNull())
            genOutput(stringBuilder, fullNamespaces, query, context, conditionList, "AND");
    }
}
