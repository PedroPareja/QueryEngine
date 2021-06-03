package es.pedropareja.database.generic.querygen.base;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTable;
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

    @SuppressWarnings("unchecked")
    public Set<DBTable> getAutoTables()
    {
        Set<DBTable> result = new TreeSet<>((a,b)-> a.getId().hashCode() - b.getId().hashCode());
        QGQueryBase queryElement = this;

        while(queryElement != null)
        {
            if(queryElement instanceof QGAutoFields && queryElement.optionalAppearanceValue == true)
            {
                List<DBFieldInfo> autoFields = ((QGAutoFields) queryElement).getAutoFields();

                if(autoFields != null)
                    for (DBFieldInfo field : autoFields)
                        result.add(field.getParentTable());
            }

            queryElement = queryElement.next;
        }

        return result;
    }
}
