package es.pedropareja.database.generic.querygen.expression.base;

public abstract class QGExpressionBase implements QGExpressionPrv
{
    private final QGExpressionPrv init;
    private QGExpressionPrv next;

    public QGExpressionBase(QGExpressionPrv init)
    {
        this.init = init;
    }

    public QGExpressionBase()
    {
        this.init = this;
    }

    public QGExpressionPrv getInit()
    {
        return init;
    }

    public QGExpressionPrv getNext()
    {
        return next;
    }

    public void setNext(QGExpressionPrv next)
    {
        this.next = next;
    }

    @Override
    public <T extends QGExpressionPrv> T assignNext(T next)
    {
        this.next = next;
        return next;
    }

    @Override
    public String toString()
    {
        return toString(null);
    }

    public <T> String toString(T context)
    {
        StringBuilder stringBuilder = new StringBuilder();
        genExpressionOutput(stringBuilder, true, context);
        return stringBuilder.toString();
    }

    protected void printSpaceIfNotFirst(StringBuilder stringBuilder)
    {
        if(init != this)
            stringBuilder.append(" ");
    }
}
