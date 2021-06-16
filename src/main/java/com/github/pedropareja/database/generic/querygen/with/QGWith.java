package com.github.pedropareja.database.generic.querygen.with;

import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.with.as.QGLinkWithAs;

public interface QGWith
    extends QGQuery, QGLinkWithAs
{
    QGWith recursive();
    QGWith id(String id);
}
