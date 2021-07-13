package com.github.pedropareja.database.generic.querygen.expression.operator;

import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import com.github.pedropareja.database.generic.querygen.expression.aggregate.QGLinkAggregate;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGLinkCase;
import com.github.pedropareja.database.generic.querygen.expression.coalesce.QGLinkCoalesce;
import com.github.pedropareja.database.generic.querygen.expression.count.QGLinkCount;
import com.github.pedropareja.database.generic.querygen.expression.field.QGLinkField;
import com.github.pedropareja.database.generic.querygen.expression.function.QGLinkFunction;
import com.github.pedropareja.database.generic.querygen.expression.id.QGLinkId;
import com.github.pedropareja.database.generic.querygen.expression.number.QGLinkNumber;
import com.github.pedropareja.database.generic.querygen.expression.parenthesis.QGLinkParenthesis;
import com.github.pedropareja.database.generic.querygen.expression.subquery.QGLinkSubQuery;

public interface QGOperator
        extends QGExpression, QGLinkField, QGLinkId, QGLinkCoalesce, QGLinkNumber,
            QGLinkAggregate, QGLinkCount, QGLinkFunction, QGLinkParenthesis, QGLinkCase,
            QGLinkSubQuery, QGLinkAs
{
    enum Type
    {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/");

        private final String symbol;

        Type(String symbol)
        {
            this.symbol = symbol;
        }

        String getSymbol()
        {
            return symbol;
        }
    }
}
