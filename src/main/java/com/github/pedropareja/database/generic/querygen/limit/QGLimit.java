package com.github.pedropareja.database.generic.querygen.limit;

import com.github.pedropareja.database.generic.querygen.offset.QGLinkOffset;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptional;

public interface QGLimit
        extends QGQuery, QGLinkOffset, QGLinkOptional<QGLimit>
{}
