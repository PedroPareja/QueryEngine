package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;

public interface QGConditionAll<T extends QGOptionalityEnabled>
        extends QGLinkConditions<QGConditionAll<T>>, QGOptionalityEnabled
{
    T end();
}
