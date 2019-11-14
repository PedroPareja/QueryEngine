package es.pedropareja.database.generic.querygen.expression.id;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;

public class QGIdPrv extends QGExpressionBase implements QGId, QGLinkOperatorsPrv
{
    private final String id;

    public QGIdPrv(QGExpressionPrv init, String id)
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
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(getInit() != this)
            stringBuilder.append(" ");

        stringBuilder.append(id);
    }

    @Override
    protected List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }
}
