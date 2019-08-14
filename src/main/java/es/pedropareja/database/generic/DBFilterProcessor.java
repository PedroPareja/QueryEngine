package es.pedropareja.database.generic;

import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;

import java.sql.SQLException;

public interface DBFilterProcessor<T>
{
    DBFilterQueryCondition<T> getQueryCondition();
    DBFilterParamSetter<T> getParamSetter();

    @FunctionalInterface
    interface DBFilterQueryCondition<T>
    {
        void doAction(QGLinkConditions<?> conditionLink, T filter);
    }

    @FunctionalInterface
    interface DBFilterParamSetter<T>
    {
        void doAction(QueryParamsHelper params, T filter) throws SQLException;
    }
}
