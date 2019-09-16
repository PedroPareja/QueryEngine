package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBFilterProcessor;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.Collection;
import java.util.List;

public interface QGLinkConditionsPrv<T extends QGQuery & QGLinkConditions<T>, U extends QGQueryMiddleEnd> extends QGLinkConditions<T>, QGLinkOptionalPrv<T, U>
{
    List<QGConditionBase> getConditionList();

    T getThis();

    U getPrv();

    @Override
    default <U extends Enum<?> & DBFieldInfo> T equals(U field)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(new QGConditionEquals<>(field));

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T equals(U field1, U field2)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(new QGConditionEquals<>(field1, field2));

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T equalsAny(U field)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(new QGConditionEqualsAny<>(field));

        return getThis();
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
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(new QGConditionIn<>(field, numberOfParameters));

        return getThis();
    }

    @Override
    default <U extends Enum<?> & DBFieldInfo> T in(U field, Collection<?> collection)
    {
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(new QGConditionIn<>(field, collection.size()));

        return getThis();
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
        if(getPrv().getNextOptionalAppearanceValueAndReset())
            getConditionList().add(new QGConditionLike<>(field));

        return getThis();
    }
}
