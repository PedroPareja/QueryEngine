package com.github.pedropareja.database.generic.querygen.expression.table;

import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;

import java.util.Collections;
import java.util.List;

public class QGTablePrv extends QGExpressionBase
    implements QGTable, QGLinkAsPrv
{
    private final DBTable table;

    public QGTablePrv(QGExpressionPrv init, DBTable table)
    {
        super(init);
        this.table = table;
    }

    public QGTablePrv(DBTable table)
    {
        this.table = table;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        QGQueryBase.printTablePath(stringBuilder, table, context);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return table.getFields().length > 0 ? table.getFields()[0].getAutoFields() : Collections.emptyList();
    }
}
