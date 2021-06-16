package com.github.pedropareja.database.generic.querygen.expression.base;

import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;

public interface QGExpression extends QGAutoFields
{
    <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context);

    boolean isComplex();
}
