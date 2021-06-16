package com.github.pedropareja.database.generic.querygen.condition.group;

import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;

public interface QGConditionAll<T extends QGOptionalityEnabled & QGInitReferenced>
        extends QGConditionGroup<QGConditionAll<T>,T>
{}
