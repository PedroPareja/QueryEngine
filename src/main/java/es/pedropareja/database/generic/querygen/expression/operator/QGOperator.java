package es.pedropareja.database.generic.querygen.expression.operator;

import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.coalesce.QGLinkCoalesce;
import es.pedropareja.database.generic.querygen.expression.field.QGLinkField;
import es.pedropareja.database.generic.querygen.expression.id.QGLinkId;

public interface QGOperator
        extends QGExpression, QGLinkField, QGLinkId, QGLinkCoalesce
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
