package com.github.pedropareja.database.generic.querygen.merge.update;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.merge.when.QGLinkMergeWhen;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;

public interface QGMergeUpdate extends QGQuery, QGLinkMergeWhen, QGLinkOptional<QGMergeUpdate>
{
    QGMergeUpdate set(DBFieldInfo field, QGExpression value);
}
