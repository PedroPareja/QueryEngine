package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;

public class QGConditionAllPrv<T extends QGOptionalityEnabled & QGInitReferenced>
    extends QGConditionGroupPrv<QGConditionAll<T>,T>
    implements QGConditionAll<T>
{

    public QGConditionAllPrv(QGQueryInit init, T parent)
    {
        super(init, parent);
    }


    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(!isNull())
        {
            boolean conditionWritten = false;

            stringBuilder.append(" (");

            for (QGConditionBase condition: conditionList)
            {
                if(!condition.isNull())
                {
                    if(conditionWritten)
                        stringBuilder.append(" AND");

                    condition.genOutput(stringBuilder, fullNamespaces, context);
                    conditionWritten = true;
                }
            }

            stringBuilder.append(")");
        }

    }

    @Override
    public boolean isNull()
    {
        if(conditionList.isEmpty())
            return true;

        for(QGConditionBase condition: conditionList)
            if(!condition.isNull())
                return false;

        return true;
    }
}
