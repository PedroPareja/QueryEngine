package es.pedropareja.database.generic.querygen.expression.operator;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.field.QGLinkFieldPrv;
import es.pedropareja.database.generic.querygen.expression.id.QGLinkIdPrv;

public class QGOperatorPrv extends QGExpressionBase
        implements QGOperator, QGLinkFieldPrv, QGLinkIdPrv
{
    private final Type type;

    public QGOperatorPrv(QGExpressionBase init, Type type)
    {
        super(init);
        this.type = type;
    }

    @Override
    protected <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" ").append(type.getSymbol());
    }
}
