package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.condition.QGConditionComparation.ComparationType;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Collection;
import java.util.List;

public interface QGLinkConditionsPrv<T extends QGQuery & QGLinkConditions<T>, U extends QGQueryMiddleEnd> extends QGLinkConditions<T>, QGLinkOptionalPrv<T, U>
{
    List<QGConditionBase> getConditionList();

    T getThis();

    U getPrv();

    default <U extends Enum<?> & DBFieldInfo> T addCondition(QGConditionBase condition)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(condition);

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T equals(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.EQUALS, field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T equals(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.EQUALS, field1, field2));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T equalsAny(U field)
    {
        return addCondition(new QGConditionEqualsAny<>(field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T exists(QGQuery query)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
        {
            getConditionList().add(new QGConditionExists(query));
            getPrv().getInit().setFullNamespaces();
        }

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T in(U field, QGQuery query)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
        {
            getConditionList().add(new QGConditionInQuery<>(field, query));
            getPrv().getInit().setFullNamespaces();
        }

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T in(U field, int numberOfParameters)
    {
        return addCondition(new QGConditionIn<>(field, numberOfParameters));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T in(U field, Collection<?> collection)
    {
        return addCondition(new QGConditionIn<>(field, collection.size()));
    }

    @Override
    default <U, F extends Enum<?> & DBFilterProcessor<U>> T applyFilter(Class<F> filterProcessorType, U filter)
    {
        for(F filterRule: filterProcessorType.getEnumConstants())
            filterRule.getQueryCondition().doAction(getThis(), filter);

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T like(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LIKE, field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T greater(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER, field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T greater(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER, field1, field2));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T greaterOrEqual(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER_EQUAL, field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T greaterOrEqual(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.GREATER_EQUAL, field1, field2));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T less(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS, field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T less(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS, field1, field2));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T lessOrEqual(U field)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS_EQUAL, field));
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T lessOrEqual(U field1, U field2)
    {
        return addCondition(new QGConditionComparation<>(ComparationType.LESS_EQUAL, field1, field2));
    }
}
