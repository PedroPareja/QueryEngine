package es.pedropareja.database.generic.querygen.auto;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTableMapper;
import es.pedropareja.database.generic.DBTableMapper.FieldEquity;
import es.pedropareja.database.generic.DBTableMapper.Solution;
import es.pedropareja.database.generic.DBTableMapper.TableJoin;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.from.QGLinkFrom;
import es.pedropareja.database.generic.querygen.join.QGLinkJoin;
import es.pedropareja.database.generic.querygen.on.QGOn;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Set;
import java.util.TreeSet;

public class QGAutoPrv<T extends Enum<?> & DBFieldInfo>
        extends QGQueryMiddleEnd
        implements QGAuto, QGLinkOptionalPrv<QGAuto>
{
    private final DBTableMapper tableMapper;
    private final Class<T> mainTable;

    public QGAutoPrv(DBTableMapper tableMapper, Class<T> mainTable, QGQueryInit init)
    {
        super(init);
        this.tableMapper = tableMapper;
        this.mainTable = mainTable;
        init.setFullNamespaces();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        Set<Class<? extends DBFieldInfo>> autoTables = new TreeSet<>((a,b) -> a.hashCode() - b.hashCode());
        autoTables.addAll(getInit().getAutoTables());

        Class<T> fromTable = mainTable != null ? mainTable : (Class<T>) autoTables.toArray()[0];
        autoTables.remove(fromTable);

        QGLinkJoin linkJoin = new NullInit().from(fromTable);

        linkJoin.getInit().setFullNamespaces();

        Solution joinsSolution = tableMapper.solve(fromTable, autoTables);

        for(TableJoin tableJoin: joinsSolution.getTableJoins())
        {
            QGOn qgOn = linkJoin.join((Class<T>) tableJoin.getJoinTable()).on();

            for(FieldEquity fieldEquity: tableJoin.getFieldEquities())
                qgOn.equals((T)fieldEquity.getField1(), (T)fieldEquity.getField2());

            linkJoin = qgOn;
        }

        ((QGQueryBase)linkJoin).getInit().genOutput(stringBuilder, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGAuto getThis()
    {
        return this;
    }

    private static class NullInit extends QGQueryInit implements QGLinkFrom
    {
        @Override
        public <T> void genOutput(StringBuilder stringBuilder, T context)
        {
            genOutputNext(stringBuilder, context);
        }
    }
}
