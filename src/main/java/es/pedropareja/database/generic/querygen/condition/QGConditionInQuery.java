package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

import java.util.Arrays;
import java.util.List;

public class QGConditionInQuery<T extends DBFieldInfo> implements QGConditionBase
{
    private final T field;
    private final QGQuery query;

    public QGConditionInQuery(T field, QGQuery query)
    {
        this.field = field;
        this.query = query;
        query.getInit().setFullNamespaces();
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");
        QGQueryBase.printField(stringBuilder, field, true, context);
        stringBuilder.append(" IN(");
        query.getInit().genOutput(stringBuilder, context);
        stringBuilder.append(")");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionInQuery))
            return false;

        QGConditionInQuery<?> o = (QGConditionInQuery)obj;

        return field.equalsField(o.field) && query.equals(o.query);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(field);
    }
}
