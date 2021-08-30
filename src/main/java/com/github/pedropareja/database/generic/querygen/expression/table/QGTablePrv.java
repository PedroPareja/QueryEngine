package com.github.pedropareja.database.generic.querygen.expression.table;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
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
    private final String alias;

    public QGTablePrv(QGExpressionPrv init, DBTable table, String alias)
    {
        super(init);
        this.table = table;
        this.alias = alias;
    }

    public QGTablePrv(QGExpressionPrv init, DBTable table)
    {
        this(init, table, null);
    }

    public QGTablePrv(DBTable table, String alias)
    {
        this.table = table;
        this.alias = alias;
    }

    public QGTablePrv(DBTable table)
    {
        this(table, null);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        QGQueryBase.printTablePath(stringBuilder, table, context);

        if(alias != null && !alias.isEmpty())
            stringBuilder.append(" ").append(alias);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return table.getFields().length > 0 ? table.getFields()[0].getAutoFields() : Collections.emptyList();
    }
}
