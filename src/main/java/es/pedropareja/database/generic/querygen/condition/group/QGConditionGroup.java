package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;

public interface QGConditionGroup<U extends QGConditionGroup<U,T>, T extends QGOptionalityEnabled & QGInitReferenced>
        extends QGLinkConditions<U>, QGOptionalityEnabled, QGInitReferenced
{

}
