package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;

public interface QGConditionAll<T extends QGOptionalityEnabled & QGInitReferenced>
        extends QGLinkConditions<QGConditionAll<T>>, QGOptionalityEnabled, QGInitReferenced
{
    T end();
}
