package es.pedropareja.database.generic.querygen.limit;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.offset.QGLinkOffset;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptional;

public interface QGLimit
        extends QGQuery, QGLinkOffset, QGLinkOptional<QGLimit>
{}
