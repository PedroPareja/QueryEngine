package es.pedropareja.database.generic.querygen.optional;

import es.pedropareja.database.generic.querygen.base.QGQuery;

public interface QGLinkOptional<T extends QGQuery>
{
    T optional(boolean appearanceOption);
}
