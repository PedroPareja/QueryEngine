package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.condition.group.QGConditionAll;
import es.pedropareja.database.generic.querygen.condition.group.QGConditionAny;
import es.pedropareja.database.generic.querygen.condition.group.QGConditionNot;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;

import java.util.Collection;

public interface QGLinkConditions<T extends QGLinkConditions<T>> extends QGLinkOptional<T>, QGOptionalityEnabled, QGInitReferenced
{
    T equals(QGExpression exp);
    T equals(QGExpression exp1, QGExpression exp2);
    <U extends DBFieldInfo> T equalsAny(U field);
    <U extends DBFieldInfo> T exists(QGQuery query);
    <U extends DBFieldInfo> T in(U field, QGQuery query);
    <U extends DBFieldInfo> T in(U field, int numberOfParameters);
    <U extends DBFieldInfo> T in(U field, Collection<?> collection);
    T like(QGExpression exp);
    T like(QGExpression exp1, QGExpression exp2);
    T greater(QGExpression exp);
    T greater(QGExpression exp1, QGExpression exp2);
    T greaterOrEqual(QGExpression exp);
    T greaterOrEqual(QGExpression exp1, QGExpression exp2);
    T less(QGExpression exp);
    T less(QGExpression exp1, QGExpression exp2);
    T lessOrEqual(QGExpression exp);
    T lessOrEqual(QGExpression exp1, QGExpression exp2);
    T isNull(QGExpression exp);

    QGConditionAll<T> all();
    QGConditionAny<T> any();
    QGConditionNot<T> not();

    <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilter(Class<F> filterProcessorType, U filter);
    <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilters(Class<F> filterProcessorType, Collection<U> filters);
}
