package com.github.pedropareja.database.generic.querygen.condition.group;

import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;

public interface QGConditionGroup<U extends QGConditionGroup<U,T>, T extends QGOptionalityEnabled & QGInitReferenced>
        extends QGLinkConditions<U>, QGOptionalityEnabled, QGInitReferenced, QGConditionBase
{
    T end();
}
