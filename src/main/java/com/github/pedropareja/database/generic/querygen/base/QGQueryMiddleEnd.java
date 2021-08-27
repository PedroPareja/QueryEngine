package com.github.pedropareja.database.generic.querygen.base;

import com.github.pedropareja.database.generic.DBTableAliasIndex;

public abstract class QGQueryMiddleEnd extends QGQueryBase
{
    private final QGQueryInit init;

    protected QGQueryMiddleEnd(QGQueryInit init)
    {
        this.init = init;
    }

    @Override
    public QGQueryInit getInit()
    {
        return init;
    }

    public void setOptionalAppearanceValue(boolean optionalAppearanceValue)
    {
        this.optionalAppearanceValue = optionalAppearanceValue;
    }

    @Override
    public DBTableAliasIndex getTableAliasIndex()
    {
        return getInit().getTableAliasIndex();
    }

    @Override
    protected void setTableAliasIndex(DBTableAliasIndex tableAliasIndex)
    {
        getInit().setTableAliasIndex(tableAliasIndex);
    }

    @Override
    public String toString()
    {
        return init.genQuery(null);
    }

    @Override
    public <T> String toString(T context)
    {
        return init.genQuery(context);
    }
}
