package es.pedropareja.database.generic.querygen.expression.param;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

import java.util.List;

public class QGParamPrv extends QGExpressionBase implements QGParam
{
    public QGParamPrv() {}

    public QGParamPrv(QGExpressionPrv init)
    {
        super(init);
    }

    @Override
    protected List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }


    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append("?");
    }
}
