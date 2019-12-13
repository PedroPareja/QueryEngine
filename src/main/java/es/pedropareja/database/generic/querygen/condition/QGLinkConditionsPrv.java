package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.condition.QGConditionComparation.ComparationType;
import es.pedropareja.database.generic.querygen.condition.group.*;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Collection;
import java.util.List;

public interface QGLinkConditionsPrv<T extends QGOptionalityEnabled & QGLinkConditions<T> & QGInitReferenced>
        extends QGLinkConditions<T>, QGLinkOptionalPrv<T>
{
    List<QGConditionBase> getConditionList();

    T getThis();

    default <U extends DBFieldInfo> T addCondition(QGConditionBase condition)
    {
        if(getThis().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(condition);

        return getThis();
    }

    @Override
    default T equals(QGExpression exp)
    {
        return addCondition(new QGConditionComparation(ComparationType.EQUALS, exp));
    }

    @Override
    default T equals(QGExpression exp1, QGExpression exp2)
    {
        return addCondition(new QGConditionComparation(ComparationType.EQUALS, exp1, exp2));
    }

    @Override
    default <U extends DBFieldInfo> T equalsAny(U field)
    {
        return addCondition(new QGConditionEqualsAny<>(field));
    }

    @Override
    default <U extends DBFieldInfo> T exists(QGQuery query)
    {
        if(getThis().getNextOptionalAppearanceValueAndReset())
        {
            getConditionList().add(new QGConditionExists(query));

            if(getThis().getInit() != null)
                getThis().getInit().setFullNamespaces();
        }

        return getThis();
    }

    @Override
    default <U extends DBFieldInfo> T in(U field, QGQuery query)
    {
        if(getThis().getNextOptionalAppearanceValueAndReset())
        {
            getConditionList().add(new QGConditionInQuery<>(field, query));

            if(getThis().getInit() != null)
                getThis().getInit().setFullNamespaces();
        }

        return getThis();
    }

    @Override
    default <U extends DBFieldInfo> T in(U field, int numberOfParameters)
    {
        return addCondition(new QGConditionIn<>(field, numberOfParameters));
    }

    @Override
    default <U extends DBFieldInfo> T in(U field, Collection<?> collection)
    {
        return addCondition(new QGConditionIn<>(field, collection != null ? collection.size() : 0));
    }

    @Override
    default <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilter(Class<F> filterProcessorType, U filter)
    {
        if(filter != null)
            for(F filterRule: filterProcessorType.getEnumConstants())
                filterRule.getQueryCondition().doAction(getThis(), filter);

        return getThis();
    }

    @Override
    default <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilters(Class<F> filterProcessorType, Collection<U> filters)
    {
        if(filters != null)
            for(U filter: filters)
                applyFilter(filterProcessorType, filter);

        return getThis();
    }

    @Override
    default T like(QGExpression exp)
    {
        return addCondition(new QGConditionComparation(ComparationType.LIKE, exp));
    }

    @Override
    default T like(QGExpression exp1, QGExpression exp2)
    {
        return addCondition(new QGConditionComparation(ComparationType.LIKE, exp1, exp2));
    }

    @Override
    default T greater(QGExpression exp)
    {
        return addCondition(new QGConditionComparation(ComparationType.GREATER, exp));
    }

    @Override
    default T greater(QGExpression exp1, QGExpression exp2)
    {
        return addCondition(new QGConditionComparation(ComparationType.GREATER, exp1, exp2));
    }

    @Override
    default T greaterOrEqual(QGExpression exp)
    {
        return addCondition(new QGConditionComparation(ComparationType.GREATER_EQUAL, exp));
    }

    @Override
    default T greaterOrEqual(QGExpression exp1, QGExpression exp2)
    {
        return addCondition(new QGConditionComparation(ComparationType.GREATER_EQUAL, exp1, exp2));
    }

    @Override
    default T less(QGExpression exp)
    {
        return addCondition(new QGConditionComparation(ComparationType.LESS, exp));
    }

    @Override
    default T less(QGExpression exp1, QGExpression exp2)
    {
        return addCondition(new QGConditionComparation(ComparationType.LESS, exp1, exp2));
    }

    @Override
    default T lessOrEqual(QGExpression exp)
    {
        return addCondition(new QGConditionComparation(ComparationType.LESS_EQUAL, exp));
    }

    @Override
    default T lessOrEqual(QGExpression exp1, QGExpression exp2)
    {
        return addCondition(new QGConditionComparation(ComparationType.LESS_EQUAL, exp1, exp2));
    }

    @Override
    default QGConditionAll<T> all()
    {
        QGConditionAll<T> result = new QGConditionAllPrv<>(getInit(), getThis());
        addCondition(result);
        return result;
    }

    @Override
    default QGConditionAny<T> any()
    {
        QGConditionAny<T> result = new QGConditionAnyPrv<>(getInit(), getThis());
        addCondition(result);
        return result;
    }

    @Override
    default QGConditionNot<T> not()
    {
        QGConditionNot<T> result = new QGConditionNotPrv<>(getInit(), getThis());
        addCondition(result);
        return result;
    }
}
