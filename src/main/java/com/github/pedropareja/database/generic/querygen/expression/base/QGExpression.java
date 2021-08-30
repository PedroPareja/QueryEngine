package com.github.pedropareja.database.generic.querygen.expression.base;

import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGExpression extends QGAutoFields
{
    <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context);

    boolean isComplex();
}
