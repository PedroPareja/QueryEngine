package com.github.pedropareja.database.generic.querygen.expression.operator;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGLinkCasePrv;
import com.github.pedropareja.database.generic.querygen.expression.id.QGLinkIdPrv;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.expression.aggregate.QGLinkAggregatePrv;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.coalesce.QGLinkCoalescePrv;
import com.github.pedropareja.database.generic.querygen.expression.count.QGLinkCountPrv;
import com.github.pedropareja.database.generic.querygen.expression.field.QGLinkFieldPrv;
import com.github.pedropareja.database.generic.querygen.expression.function.QGLinkFunctionPrv;
import com.github.pedropareja.database.generic.querygen.expression.number.QGLinkNumberPrv;

import java.util.List;

public class QGOperatorPrv extends QGExpressionBase
        implements QGOperator, QGLinkFieldPrv, QGLinkIdPrv, QGLinkCoalescePrv,
            QGLinkNumberPrv, QGLinkAggregatePrv, QGLinkCountPrv, QGLinkFunctionPrv,
        QGLinkCasePrv, QGLinkAsPrv
{
    private final Type type;

    public QGOperatorPrv(QGExpressionPrv init, Type type)
    {
        super(init);
        this.type = type;
    }

    public QGOperatorPrv(Type type)
    {
        super();
        this.type = type;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append(type.getSymbol());
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }
}
