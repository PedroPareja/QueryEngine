package com.github.pedropareja.database.generic.querygen;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.delete.QGDelete;
import com.github.pedropareja.database.generic.querygen.expression.QGExprGen;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.insert.QGInsert;
import com.github.pedropareja.database.generic.querygen.insert.QGInsertPrv;
import com.github.pedropareja.database.generic.querygen.merge.QGMerge;
import com.github.pedropareja.database.generic.querygen.merge.QGMergePrv;
import com.github.pedropareja.database.generic.querygen.select.QGSelect;
import com.github.pedropareja.database.generic.querygen.update.QGUpdate;
import com.github.pedropareja.database.generic.querygen.update.QGUpdatePrv;
import com.github.pedropareja.database.generic.querygen.with.QGWith;
import com.github.pedropareja.database.generic.querygen.with.QGWithPrv;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.querygen.delete.QGDeletePrv;
import com.github.pedropareja.database.generic.querygen.select.QGSelectPrv;


public class QueryGen
{
    public static QGSelect select() { return new QGSelectPrv(); }

    @SafeVarargs
    public static QGSelect select(QGExpression... expressions) { return new QGSelectPrv(expressions); }

    @SafeVarargs
    public static QGInsert insert(DBFieldInfo... fields) { return new QGInsertPrv(fields); }

    public static QGUpdate update(DBTable table) { return new QGUpdatePrv(table); }
    public static QGUpdate update(Class<? extends DBTable> tableType) { return update(QGQueryBase.getTableInstance(tableType)); }

    public static QGDelete delete(DBTable table) { return new QGDeletePrv(table); }
    public static QGDelete delete(Class<? extends DBTable> tableType) { return delete(QGQueryBase.getTableInstance(tableType)); }

    public static QGMerge merge(DBTable table) { return new QGMergePrv(table); }
    public static QGMerge merge(Class<? extends DBTable> tableType) { return merge(QGQueryBase.getTableInstance(tableType)); }

    public static QGWith with(QGExpression... fields)
    {
        return new QGWithPrv(fields);
    }

    public static QGWith with(String... fieldsStr)
    {
        QGExpression[] fields = new QGExpression[fieldsStr.length];

        for(int i=0; i < fieldsStr.length; i++)
            fields[i] = QGExprGen.id(fieldsStr[i]);

        return with(fields);
    }
}
