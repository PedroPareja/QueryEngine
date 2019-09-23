package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.auto.QGAutoFields;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class QGQueryInit extends QGQueryBase
{
    protected boolean fullNamespaces = false;

    public void setFullNamespaces()
    {
        fullNamespaces = true;
    }

    public boolean isFullNamespaces()
    {
        return fullNamespaces;
    }

    @Override
    public QGQueryInit getInit()
    {
        return this;
    }

    protected <T> String genQuery(T context)
    {
        StringBuilder stringBuilder = new StringBuilder();
        genOutput(stringBuilder, context);
        return stringBuilder.toString();
    }

    @Override
    public String toString()
    {
        return genQuery(null);
    }

    @Override
    public <T> String toString(T context)
    {
        return genQuery(context);
    }

    public <T extends Enum<?> & DBFieldInfo> Set<Class<T>> getAutoTables()
    {
        Set<Class<T>> result = new TreeSet<>((a,b)-> a.hashCode() - b.hashCode());
        QGQueryBase queryElement = this;

        while(queryElement != null)
        {
            if(queryElement instanceof QGAutoFields)
                for(DBFieldInfo field: ((QGAutoFields)queryElement).getAutoFields())
                    result.add((Class<T>)field.getClass());

            queryElement = queryElement.next;
        }

        return result;
    }
}
