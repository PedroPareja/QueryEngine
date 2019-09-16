package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;

import java.util.Collection;

public interface QGLinkConditions<T extends QGQuery & QGLinkConditions<T>> extends QGLinkOptional<T>
{
    <U extends Enum<?> & DBFieldInfo> T equals(U field);
    <U extends Enum<?> & DBFieldInfo> T equals(U field1, U field2);
    <U extends Enum<?> & DBFieldInfo> T equalsAny(U field);
    <U extends Enum<?> & DBFieldInfo> T exists(QGQuery query);
    <U extends Enum<?> & DBFieldInfo> T in(U field, QGQuery query);
    <U extends Enum<?> & DBFieldInfo> T in(U field, int numberOfParameters);
    <U extends Enum<?> & DBFieldInfo> T in(U field, Collection<?> collection);
    <U extends Enum<?> & DBFieldInfo> T like(U field);
    <U extends Enum<?> & DBFieldInfo> T greater(U field);
    <U extends Enum<?> & DBFieldInfo> T greater(U field1, U field2);
    <U extends Enum<?> & DBFieldInfo> T greaterOrEqual(U field);
    <U extends Enum<?> & DBFieldInfo> T greaterOrEqual(U field1, U field2);
    <U extends Enum<?> & DBFieldInfo> T less(U field);
    <U extends Enum<?> & DBFieldInfo> T less(U field1, U field2);
    <U extends Enum<?> & DBFieldInfo> T lessOrEqual(U field);
    <U extends Enum<?> & DBFieldInfo> T lessOrEqual(U field1, U field2);

    <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilter(Class<F> filterProcessorType, U filter);
}
