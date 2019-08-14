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

    protected String genQuery()
    {
        StringBuilder stringBuilder = new StringBuilder();
        genOutput(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public String toString()
    {
        return genQuery();
    }
}
