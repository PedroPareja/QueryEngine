package es.pedropareja.database.generic.querygen.optional;

import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;

public interface QGLinkOptionalPrv<T extends QGOptionalityEnabled> extends QGLinkOptional<T>
{
    T getThis();

    @Override
    default T optional(boolean appearanceOption)
    {
        getThis().setNextOptionalAppearanceValue(appearanceOption);
        return getThis();
    }
}
