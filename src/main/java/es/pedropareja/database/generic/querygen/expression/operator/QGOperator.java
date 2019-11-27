package es.pedropareja.database.generic.querygen.expression.operator;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.coalesce.QGLinkCoalesce;
import es.pedropareja.database.generic.querygen.expression.field.QGLinkField;
import es.pedropareja.database.generic.querygen.expression.id.QGLinkId;
import es.pedropareja.database.generic.querygen.expression.number.QGLinkNumber;

public interface QGOperator
        extends QGExpression, QGLinkField, QGLinkId, QGLinkCoalesce, QGLinkNumber
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
