package com.github.pedropareja.database.generic.querygen.expression.as;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.id.QGId;

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
