package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGInitReferenced;
import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.condition.QGConditionBase;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;

public interface QGConditionNot<T extends QGOptionalityEnabled & QGInitReferenced & QGLinkConditions<T>>
    extends QGConditionBase, QGLinkConditions<T>
{}
