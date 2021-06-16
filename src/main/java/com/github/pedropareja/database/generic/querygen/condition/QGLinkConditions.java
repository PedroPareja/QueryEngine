package com.github.pedropareja.database.generic.querygen.condition;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.DBFilterContextProcessor;
import com.github.pedropareja.database.generic.DBFilterProcessor;
import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.condition.group.QGConditionAll;
import com.github.pedropareja.database.generic.querygen.condition.group.QGConditionAny;
import com.github.pedropareja.database.generic.querygen.condition.group.QGConditionNot;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;

import java.util.Collection;

public interface QGLinkConditions<T extends QGLinkConditions<T>> extends QGLinkOptional<T>, QGOptionalityEnabled, QGInitReferenced
{
    T equals(QGExpression exp);
    T equals(QGExpression exp1, QGExpression exp2);
    T equalsAny(DBFieldInfo field);
    T exists(QGQuery query);
    T in(DBFieldInfo field, QGQuery query);
    T in(DBFieldInfo[] fields, QGQuery query);
    T in(DBFieldInfo field, int numberOfParameters);
    T in(QGExpression field, Collection<QGExpression> values);
    T in(QGExpression field, QGExpression ... values);
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
    <U, C, F extends Enum<?> & DBFilterContextProcessor<U,C>> T applyFilter(Class<F> filterProcessorType, U filter, C context);
    <U, C, F extends Enum<?> & DBFilterContextProcessor<U,C>> T applyFilters(Class<F> filterProcessorType, Collection<U> filters, C context);
}
