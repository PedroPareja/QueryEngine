package com.github.pedropareja.database.generic.querygen.condition.group;

import com.github.pedropareja.database.generic.querygen.base.QGInitReferenced;
import com.github.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import com.github.pedropareja.database.generic.querygen.condition.QGConditionBase;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;

public interface QGConditionNot<T extends QGOptionalityEnabled & QGInitReferenced & QGLinkConditions<T>>
    extends QGConditionBase, QGLinkConditions<T>
{}
