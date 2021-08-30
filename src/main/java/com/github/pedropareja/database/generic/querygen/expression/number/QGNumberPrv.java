package com.github.pedropareja.database.generic.querygen.expression.number;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

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
    public List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append(value);
    }
}
