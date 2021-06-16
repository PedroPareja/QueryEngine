package com.github.pedropareja.database.generic.querygen.merge.on;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import com.github.pedropareja.database.generic.querygen.merge.when.QGLinkMergeWhen;

public interface QGMergeOn extends QGQuery, QGLinkConditions<QGMergeOn>, QGLinkMergeWhen
{

}
