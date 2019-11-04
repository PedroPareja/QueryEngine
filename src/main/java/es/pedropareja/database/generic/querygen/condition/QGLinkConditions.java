package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.condition.group.QGConditionAll;
import es.pedropareja.database.generic.querygen.condition.group.QGConditionAny;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;

import java.util.Collection;

public interface QGLinkConditions<T extends QGLinkConditions<T>> extends QGLinkOptional<T>, QGOptionalityEnabled, QGInitReferenced
{
    <U extends DBFieldInfo> T equals(U field);
    <U extends DBFieldInfo> T equals(U field1, U field2);
    <U extends DBFieldInfo> T equalsAny(U field);
    <U extends DBFieldInfo> T exists(QGQuery query);
    <U extends DBFieldInfo> T in(U field, QGQuery query);
    <U extends DBFieldInfo> T in(U field, int numberOfParameters);
    <U extends DBFieldInfo> T in(U field, Collection<?> collection);
    <U extends DBFieldInfo> T like(U field);
    <U extends DBFieldInfo> T greater(U field);
    <U extends DBFieldInfo> T greater(U field1, U field2);
    <U extends DBFieldInfo> T greaterOrEqual(U field);
    <U extends DBFieldInfo> T greaterOrEqual(U field1, U field2);
    <U extends DBFieldInfo> T less(U field);
    <U extends DBFieldInfo> T less(U field1, U field2);
    <U extends DBFieldInfo> T lessOrEqual(U field);
    <U extends DBFieldInfo> T lessOrEqual(U field1, U field2);

    QGConditionAll<T> all();
    QGConditionAny<T> any();

    <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilter(Class<F> filterProcessorType, U filter);
    <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilters(Class<F> filterProcessorType, Collection<U> filters);
}
