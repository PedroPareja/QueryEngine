package com.github.pedropareja.database.generic.querygen.expression.field;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;

public class QGFieldPrv extends QGExpressionBase
        implements QGField, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private final DBFieldInfo field;

    public QGFieldPrv(QGExpressionPrv init, DBFieldInfo field)
    {
        super(init);
        this.field = field;
    }

    public QGFieldPrv(DBFieldInfo field)
    {
        super();
        this.field = field;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, U context)
    {
        printSpaceIfNotFirst(stringBuilder);

        query.getInit().printField(stringBuilder, field, fullNamespaces, context);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return field.getAutoFields();
    }
}
