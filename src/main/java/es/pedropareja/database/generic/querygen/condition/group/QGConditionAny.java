package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;

public interface QGConditionAny<T extends QGOptionalityEnabled & QGInitReferenced>
        extends QGConditionGroup<QGConditionAny<T>,T>
{}
