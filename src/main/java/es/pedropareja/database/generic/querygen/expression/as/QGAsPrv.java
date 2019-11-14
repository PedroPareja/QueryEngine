package es.pedropareja.database.generic.querygen.expression.as;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

import java.util.List;

public class QGAsPrv extends QGExpressionBase implements QGAs
{
    private final String id;

    public QGAsPrv(QGExpressionPrv init, String id)
    {
        super(init);
        this.id = id;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" AS ").append(id);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }
}
