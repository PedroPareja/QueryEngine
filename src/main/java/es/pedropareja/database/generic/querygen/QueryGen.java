package es.pedropareja.database.generic.querygen;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.delete.QGDelete;
import es.pedropareja.database.generic.querygen.delete.QGDeletePrv;
import es.pedropareja.database.generic.querygen.insert.QGInsert;
import es.pedropareja.database.generic.querygen.insert.QGInsertPrv;
import es.pedropareja.database.generic.querygen.select.QGSelect;
import es.pedropareja.database.generic.querygen.select.QGSelectPrv;
import es.pedropareja.database.generic.querygen.update.QGUpdate;
import es.pedropareja.database.generic.querygen.update.QGUpdatePrv;


public class QueryGen
{
    public static QGSelect select() { return new QGSelectPrv<>(); }

    @SafeVarargs
    public static <T extends Enum<?> & DBFieldInfo> QGSelect select(T ... fields) { return new QGSelectPrv<>(fields); }

    @SafeVarargs
    public static <T extends Enum<?> & DBFieldInfo> QGInsert insert(T ... fields) { return new QGInsertPrv<>(fields); }

    public static <T extends Enum<?> & DBFieldInfo> QGUpdate update(Class<T> tableType) { return new QGUpdatePrv<>(tableType); }

    public static <T extends Enum<?> & DBFieldInfo> QGDelete delete(Class<T> tableType) { return new QGDeletePrv<>(tableType); }

}
