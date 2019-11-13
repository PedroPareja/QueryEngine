package es.pedropareja.database.generic.querygen.expression.base;

public interface QGExpression
{
    <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context);
}
