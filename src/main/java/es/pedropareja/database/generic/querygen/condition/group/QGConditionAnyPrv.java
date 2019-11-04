package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;

public class QGConditionAnyPrv<T extends QGOptionalityEnabled & QGInitReferenced>
        extends QGConditionGroupPrv<QGConditionAny<T>,T>
        implements QGConditionAny<T>
{
    public QGConditionAnyPrv(QGQueryInit init, T parent)
    {
        super(init, parent);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(!isNull())
            genOutput(stringBuilder, fullNamespaces, context, conditionList, "OR");
    }
}
