package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQuery;

import java.util.ArrayList;
import java.util.List;

public class QGConditionExists implements QGConditionBase
{
    private final QGQuery query;

    public QGConditionExists(QGQuery query)
    {
        this.query = query;
        query.getInit().setFullNamespaces();
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" EXISTS(");
        query.getInit().genOutput(stringBuilder, context);
        stringBuilder.append(")");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionExists))
            return false;

        return query.equals(((QGConditionExists)obj).query);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return new ArrayList<>();
    }
}
