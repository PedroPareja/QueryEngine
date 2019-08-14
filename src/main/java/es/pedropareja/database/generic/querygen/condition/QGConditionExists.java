package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.querygen.base.QGQuery;

public class QGConditionExists implements QGConditionBase
{
    private final QGQuery query;

    public QGConditionExists(QGQuery query)
    {
        this.query = query;
        query.getInit().setFullNamespaces();
    }

    @Override
    public void genOutput(StringBuilder stringBuilder, boolean fullNamespaces)
    {
        stringBuilder.append(" EXISTS(");
        query.getInit().genOutput(stringBuilder);
        stringBuilder.append(")");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionExists))
            return false;

        return query.equals(((QGConditionExists)obj).query);
    }
}
