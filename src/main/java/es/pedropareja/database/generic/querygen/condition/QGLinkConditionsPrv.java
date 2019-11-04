package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.condition.QGConditionComparation.ComparationType;
import es.pedropareja.database.generic.querygen.condition.group.*;
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
    default <U extends DBFieldInfo> T equals(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.EQUALS, field));
    }

    @Override
    default <U extends DBFieldInfo> T equals(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.EQUALS, field1, field2));
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
    default <U extends DBFieldInfo> T like(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LIKE, field));
    }

    @Override
    default <U extends DBFieldInfo> T greater(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER, field));
    }

    @Override
    default <U extends DBFieldInfo> T greater(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER, field1, field2));
    }

    @Override
    default <U extends DBFieldInfo> T greaterOrEqual(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER_EQUAL, field));
    }

    @Override
    default <U extends DBFieldInfo> T greaterOrEqual(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER_EQUAL, field1, field2));
    }

    @Override
    default <U extends DBFieldInfo> T less(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS, field));
    }

    @Override
    default <U extends DBFieldInfo> T less(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS, field1, field2));
    }

    @Override
    default <U extends DBFieldInfo> T lessOrEqual(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS_EQUAL, field));
    }

    @Override
    default <U extends DBFieldInfo> T lessOrEqual(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS_EQUAL, field1, field2));
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
