package es.pedropareja.database.generic.querygen.expression.base;

public abstract class QGExpressionBase implements QGExpressionPrv
{
    private final QGExpressionBase init;
    private QGExpressionBase next;
    private boolean fullNamespaces = false;

    public QGExpressionBase(QGExpressionBase init)
    {
        this.init = init;
    }

    public QGExpressionBase()
    {
        this.init = this;
    }

    public QGExpressionBase getInit()
    {
        return init;
    }

    public QGExpressionBase getNext()
    {
        return next;
    }

    public void setNext(QGExpressionBase next)
    {
        this.next = next;
    }

    public boolean isFullNamespaces()
    {
        return init.fullNamespaces;
    }

    public void setFullNamespaces()
    {
        init.fullNamespaces = true;
    }

    @Override
    public <T extends QGExpressionBase> T assignNext(T next)
    {
        this.next = next;
        return next;
    }

    protected abstract <T> void genOutput(StringBuilder stringBuilder, T context);

    protected <T> void getOutputSequence(StringBuilder stringBuilder, T context)
    {
        genOutput(stringBuilder, context);

        if(next != null)
            next.getOutputSequence(stringBuilder, context);
    }

    @Override
    public String toString()
    {
        return toString(null);
    }

    public <T> String toString(T context)
    {
        StringBuilder stringBuilder = new StringBuilder();
        init.getOutputSequence(stringBuilder, context);
        return stringBuilder.toString();
    }

    protected void printSpaceIfNotFirst(StringBuilder stringBuilder)
    {
        if(init != this)
            stringBuilder.append(" ");
    }
}
