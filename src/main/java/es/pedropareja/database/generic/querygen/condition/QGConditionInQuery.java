package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.exceptions.QueryGenException;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

import java.util.Arrays;
import java.util.List;

public class QGConditionInQuery implements QGConditionBase
{
    private final DBFieldInfo[] fields;
    private final QGQuery query;

    public QGConditionInQuery(DBFieldInfo field, QGQuery query)
    {
        this(new DBFieldInfo[] {field}, query);
    }

    public QGConditionInQuery(DBFieldInfo[] fields, QGQuery query)
    {
        if(fields == null || fields.length == 0)
            throw new QueryGenException("At least a field is required for the IN condition");

        this.fields = fields;
        this.query = query;
        query.getInit().setFullNamespaces();
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");

        if(fields.length > 1)
            stringBuilder.append("(");

        for(int i=0; i < fields.length; i++)
        {
            if(i!=0)
                stringBuilder.append(", ");

            QGQueryBase.printField(stringBuilder, fields[i], true, context);
        }

        if(fields.length > 1)
            stringBuilder.append(")");

        stringBuilder.append(" IN(");
        query.getInit().genOutput(stringBuilder, context);
        stringBuilder.append(")");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionInQuery))
            return false;

        QGConditionInQuery o = (QGConditionInQuery)obj;

        if(fields.length != o.fields.length)
            return false;

        for(int i=0; i < fields.length; i++)
            if(!fields[i].equalsField(o.fields[i]))
                return false;

        return query.equals(o.query);
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        return Arrays.asList(fields);
    }
}
