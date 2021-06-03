package es.pedropareja.database.generic.querygen.expression.operator;

import es.pedropareja.database.generic.querygen.expression.aggregate.QGLinkAggregate;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.casecmd.QGLinkCase;
import es.pedropareja.database.generic.querygen.expression.coalesce.QGLinkCoalesce;
import es.pedropareja.database.generic.querygen.expression.count.QGLinkCount;
import es.pedropareja.database.generic.querygen.expression.field.QGLinkField;
import es.pedropareja.database.generic.querygen.expression.function.QGLinkFunction;
import es.pedropareja.database.generic.querygen.expression.id.QGLinkId;
import es.pedropareja.database.generic.querygen.expression.number.QGLinkNumber;

public interface QGOperator
        extends QGExpression, QGLinkField, QGLinkId, QGLinkCoalesce, QGLinkNumber,
            QGLinkAggregate, QGLinkCount, QGLinkFunction, QGLinkCase, QGLinkAs
{
    enum Type
    {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        PARENTHESIS_OPEN("("),
        PARENTHESIS_CLOSE(")");

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
