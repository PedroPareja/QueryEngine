package es.pedropareja.database.generic.querygen.expression.field;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

public class QGFieldPrv<T extends Enum<?> & DBFieldInfo> extends QGExpressionBase
        implements QGField, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private final T field;

    public QGFieldPrv(QGExpressionBase init, T field)
    {
        super(init);
        this.field = field;
    }

    @Override
    protected void genOutput(StringBuilder stringBuilder)
    {
        printSpaceIfNotFirst(stringBuilder);

        QGQueryBase.printField(stringBuilder, field, isFullNamespaces());
    }
}
