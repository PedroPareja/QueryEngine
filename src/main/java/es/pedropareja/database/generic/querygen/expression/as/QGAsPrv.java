package es.pedropareja.database.generic.querygen.expression.as;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;

public class QGAsPrv extends QGExpressionBase implements QGAs
{
    private final String id;

    public QGAsPrv(QGExpressionBase init, String id)
    {
        super(init);
        this.id = id;
    }

    @Override
    protected void genOutput(StringBuilder stringBuilder)
    {
        stringBuilder.append(" AS ").append(id);
    }
}
