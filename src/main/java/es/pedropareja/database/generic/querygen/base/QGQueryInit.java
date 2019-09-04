package es.pedropareja.database.generic.querygen.base;

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
}
