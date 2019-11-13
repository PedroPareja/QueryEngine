package es.pedropareja.database.generic.querygen.expression.base;

public interface QGExpressionPrv extends QGExpression
{
    QGExpressionPrv getInit();
    QGExpressionPrv getNext();

    <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context);

    default <T> void genSequenceOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        genOutput(stringBuilder, fullNamespaces, context);

        if(getNext() != null)
            getNext().genSequenceOutput(stringBuilder, fullNamespaces, context);
    }

    @Override
    default <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        getInit().genSequenceOutput(stringBuilder, fullNamespaces, context);
    }

    <T extends QGExpressionPrv> T assignNext(T next);
}
