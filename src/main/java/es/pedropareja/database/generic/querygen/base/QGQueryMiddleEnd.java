package es.pedropareja.database.generic.querygen.base;

public abstract class QGQueryMiddleEnd extends QGQueryBase
{
    private final QGQueryInit init;

    protected QGQueryMiddleEnd(QGQueryInit init)
    {
        this.init = init;
    }

    @Override
    public QGQueryInit getInit()
    {
        return init;
    }

    public void setOptionalAppearanceValue(boolean optionalAppearanceValue)
    {
        this.optionalAppearanceValue = optionalAppearanceValue;
    }

    @Override
    public String toString()
    {
        return init.genQuery(null);
    }

    @Override
    public <T> String toString(T context)
    {
        return init.genQuery(context);
    }
}
