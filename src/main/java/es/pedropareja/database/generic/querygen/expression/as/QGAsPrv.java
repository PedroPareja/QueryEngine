package es.pedropareja.database.generic.querygen.expression.as;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import es.pedropareja.database.generic.querygen.expression.id.QGId;

import java.util.List;

public class QGAsPrv extends QGExpressionBase implements QGAs
{
    private final QGId id;

    public QGAsPrv(QGExpressionPrv init, QGId id)
    {
        super(init);
        this.id = id;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" AS ");
        id.genExpressionOutput(stringBuilder, fullNamespaces, context);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }
}
