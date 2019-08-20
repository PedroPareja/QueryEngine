package es.pedropareja.database.generic.querygen.expression.id;

import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

public class QGIdPrv extends QGExpressionBase implements QGId, QGLinkOperatorsPrv
{
    private final String id;

    public QGIdPrv(QGExpressionBase init, String id)
    {
        super(init);
        this.id = id;
    }

    @Override
    protected void genOutput(StringBuilder stringBuilder)
    {
        if(getInit() != this)
            stringBuilder.append(" ");

        stringBuilder.append(id);
    }
}
