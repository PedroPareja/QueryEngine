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

    public QGIdPrv(String id)
    {
        super();
        this.id = id;
    }

    @Override
    protected <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        if(getInit() != this)
            stringBuilder.append(" ");

        stringBuilder.append(id);
    }
}
