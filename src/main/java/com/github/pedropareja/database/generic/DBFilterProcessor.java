package com.github.pedropareja.database.generic;

import com.github.pedropareja.database.generic.DBFilterContextProcessor.DBFilterContextParamSetter;
import com.github.pedropareja.database.generic.DBFilterContextProcessor.DBFilterContextQueryCondition;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;

import java.sql.SQLException;

public interface DBFilterProcessor<T>
{
    DBFilterQueryCondition<T> getQueryCondition();
    DBFilterParamSetter<T> getParamSetter();

    @FunctionalInterface
    interface DBFilterQueryCondition<T> extends DBFilterContextQueryCondition<T, Void>
    {
        void doAction(QGLinkConditions<?> conditionLink, T filter);

        @Override
        default void doAction(QGLinkConditions<?> conditionLink, T filter, Void context)
        {
            doAction(conditionLink, filter);
        }
    }

    @FunctionalInterface
    interface DBFilterParamSetter<T> extends DBFilterContextParamSetter<T, Void>
    {
        void doAction(QueryParamsHelper params, T filter) throws SQLException;

        @Override
        default void doAction(QueryParamsHelper params, T filter, Void context) throws SQLException
        {
            doAction(params, filter);
        }
    }
}
