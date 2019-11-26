package es.pedropareja.database.generic.querygen.expression.number;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.math.BigDecimal;
import java.util.List;

public class QGNumberPrv extends QGExpressionBase implements QGNumber, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private final BigDecimal value;

    public QGNumberPrv(QGExpressionPrv init, BigDecimal value)
    {
        super(init);
        this.value = value;
    }

    public QGNumberPrv(BigDecimal value)
    {
        super();
        this.value = value;
    }

    public QGNumberPrv(QGExpressionPrv init, int value)
    {
        this(init, new BigDecimal(value));
    }

    public QGNumberPrv(QGExpressionPrv init, String value)
    {
        this(init, new BigDecimal(value));
    }

    public QGNumberPrv(int value)
    {
        this(new BigDecimal(value));
    }

    public QGNumberPrv(String value)
    {
        this(new BigDecimal(value));
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
        stringBuilder.append(value);
    }
}
