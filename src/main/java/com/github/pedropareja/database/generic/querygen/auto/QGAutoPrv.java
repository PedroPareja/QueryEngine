package com.github.pedropareja.database.generic.querygen.auto;

import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.from.QGLinkFrom;
import com.github.pedropareja.database.generic.querygen.join.QGLinkJoin;
import com.github.pedropareja.database.generic.querygen.on.QGOn;
import com.github.pedropareja.database.generic.DBTable;
import com.github.pedropareja.database.generic.DBTableMapper;
import com.github.pedropareja.database.generic.DBTableMapper.FieldEquity;
import com.github.pedropareja.database.generic.DBTableMapper.Solution;
import com.github.pedropareja.database.generic.DBTableMapper.TableJoin;
import com.github.pedropareja.database.generic.DBTableWrapper;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.join.QGJoin.JoinType;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class QGAutoPrv
        extends QGQueryMiddleEnd
        implements QGAuto, QGLinkOptionalPrv<QGAuto>
{
    private final DBTableMapper tableMapper;
    private final DBTable mainTable;
    private List<DBTable> ignoreTables = null;
    private Map<DBTable, JoinType> linkMap = null;

    public QGAutoPrv(DBTableMapper tableMapper, DBTable mainTable, QGQueryInit init)
    {
        super(init);
        this.tableMapper = tableMapper;
        this.mainTable = mainTable;
        init.setFullNamespaces();
    }

    @Override
    public QGAuto ignoreTables(DBTable ... tables)
    {
        if(ignoreTables == null)
            ignoreTables = new ArrayList<>();

        ignoreTables.addAll(Arrays.asList(tables));

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public QGAuto ignoreTables(Class<? extends DBTable>... tables)
    {
        return ignoreTables(getTableInstances(tables).toArray(new DBTable[0]));
    }

    @Override
    public QGAuto setLink(JoinType joinType, DBTable... tables)
    {
        for(DBTable table: tables)
            setLink(table, joinType);

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public QGAuto setLink(JoinType joinType, Class<? extends DBTable>... tables)
    {
        return setLink(joinType, getTableInstances(tables).toArray(new DBTable[0]));
    }

    @SuppressWarnings("unchecked")
    private List<DBTable> getTableInstances(Class<? extends DBTable>... tables)
    {
        List<DBTable> result = new ArrayList<>();

        for(Class<? extends DBTable> table: tables)
            result.add(getTableInstance(table));

        return result;
    }

    private JoinType getLink(DBTable table)
    {
        JoinType result = null;

        if(linkMap != null)
            result = linkMap.get(table);

        return result != null ? result : JoinType.INNER;
    }

    private void setLink(DBTable table, JoinType joinType)
    {
        if(linkMap == null)
            linkMap = new HashMap<>();

        linkMap.put(table, joinType);
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        Set<DBTableWrapper> autoTables = new TreeSet<>();
        Set<DBTable> autoTablesSource = getInit().getAutoTables();

        for(DBTable table: autoTablesSource)
            autoTables.add(new DBTableWrapper(table));

        DBTable fromTable = mainTable != null ? mainTable : autoTables.toArray(new DBTableWrapper[0])[0].getTable();
        autoTables.remove(new DBTableWrapper(fromTable));

        if(ignoreTables != null)
            for(DBTable table: ignoreTables)
                autoTables.remove(new DBTableWrapper(table));

        QGLinkJoin linkJoin = new NullInit().from(fromTable);

        linkJoin.getInit().setFullNamespaces();

        Solution joinsSolution = tableMapper.solve(fromTable, autoTables.stream().map(DBTableWrapper::getTable).collect(Collectors.toList()));

        for(TableJoin tableJoin: joinsSolution.getTableJoins())
        {
            QGOn qgOn = linkJoin.join(tableJoin.getJoinTable(), getLink(tableJoin.getJoinTable())).on();

            for(FieldEquity fieldEquity: tableJoin.getFieldEquities())
                qgOn.equals(fieldEquity.getField1(), fieldEquity.getField2());

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
