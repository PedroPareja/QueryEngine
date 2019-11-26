package es.pedropareja.database.generic.querygen.expression.operator;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import es.pedropareja.database.generic.querygen.expression.coalesce.QGLinkCoalescePrv;
import es.pedropareja.database.generic.querygen.expression.field.QGLinkFieldPrv;
import es.pedropareja.database.generic.querygen.expression.id.QGLinkIdPrv;
import es.pedropareja.database.generic.querygen.expression.number.QGLinkNumberPrv;

import java.util.List;

public class QGOperatorPrv extends QGExpressionBase
        implements QGOperator, QGLinkFieldPrv, QGLinkIdPrv, QGLinkCoalescePrv, QGLinkNumberPrv
{
    private final Type type;

    public QGOperatorPrv(QGExpressionPrv init, Type type)
    {
        super(init);
        this.type = type;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append(type.getSymbol());
    }

    @Override
    protected List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }
}
