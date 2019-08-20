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

    protected abstract void genOutput(StringBuilder stringBuilder);

    protected void getOutputSequence(StringBuilder stringBuilder)
    {
        genOutput(stringBuilder);

        if(next != null)
            next.getOutputSequence(stringBuilder);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        init.getOutputSequence(stringBuilder);
        return stringBuilder.toString();
    }

    protected void printSpaceIfNotFirst(StringBuilder stringBuilder)
    {
        if(init != this)
            stringBuilder.append(" ");
    }
}
