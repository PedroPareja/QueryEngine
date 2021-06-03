package es.pedropareja.database.generic.querygen.expression.base;

import es.pedropareja.database.generic.querygen.auto.QGAutoFields;

public interface QGExpression extends QGAutoFields
{
    <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context);

    boolean isComplex();
}
