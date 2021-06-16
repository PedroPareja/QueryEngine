package com.github.pedropareja.database.generic;

import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;

import java.sql.SQLException;

public interface DBFilterContextProcessor<T,C>
{
    DBFilterContextQueryCondition<T,C> getQueryCondition();
    DBFilterContextParamSetter<T,C> getParamSetter();

    @FunctionalInterface
    interface DBFilterContextQueryCondition<T,C>
    {
        void doAction(QGLinkConditions<?> conditionLink, T filter, C context);
    }

    @FunctionalInterface
    interface DBFilterContextParamSetter<T,C>
    {
        void doAction(QueryParamsHelper params, T filter, C context) throws SQLException;
    }
}
