package es.pedropareja.database.generic.querygen.optional;

import es.pedropareja.database.generic.querygen.base.QGLinkBase;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public interface QGLinkOptionalPrv<T extends QGQuery, U extends QGQueryMiddleEnd> extends QGLinkOptional<T>, QGLinkBase
{
    T getThis();

    U getPrv();

    @Override
    default T optional(boolean appearanceOption)
    {
        getPrv().setNextOptionalAppearanceValue(appearanceOption);
        return getThis();
    }
}
