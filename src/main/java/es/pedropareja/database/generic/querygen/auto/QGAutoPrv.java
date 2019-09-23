package es.pedropareja.database.generic.querygen.auto;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTableMapper;
import es.pedropareja.database.generic.DBTableMapper.Solution;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.from.QGLinkFrom;
import es.pedropareja.database.generic.querygen.join.QGLinkJoin;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Set;
import java.util.TreeSet;

public class QGAutoPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGAuto, QGLinkOptionalPrv<QGAuto, QGAutoPrv<T>>
{
    private final DBTableMapper tableMapper;
    private final Class<T> mainTable;

    public QGAutoPrv(DBTableMapper tableMapper, Class<T> mainTable, QGQueryInit init)
    {
        super(init);
        this.tableMapper = tableMapper;
        this.mainTable = mainTable;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        Set<Class<? extends DBFieldInfo>> autoTables = new TreeSet<>((a,b) -> a.hashCode() - b.hashCode());
        autoTables.addAll(getInit().getAutoTables());

        Class<T> fromTable = mainTable != null ? mainTable : (Class<T>) autoTables.toArray()[0];
        autoTables.remove(fromTable);

        QGLinkJoin linkJoin = new NullInit().from(fromTable);

        if(!autoTables.isEmpty())
            linkJoin.getInit().setFullNamespaces();

        Solution joinsSolution = tableMapper.solve(fromTable, autoTables);

        

    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGAutoPrv))
            return false;

        QGAutoPrv instance = (QGAutoPrv) q;

        return tableMapper.equals(instance.tableMapper);
    }

    @Override
    public QGAuto getThis()
    {
        return this;
    }

    @Override
    public QGAutoPrv getPrv()
    {
        return this;
    }

    private static class NullInit extends QGQueryInit implements QGLinkFrom
    {
        @Override
        public <T> void genOutput(StringBuilder stringBuilder, T context) {}

        @Override
        public boolean equalsUntilHere(QGQueryBase q)
        {
            return false;
        }
    }
}
