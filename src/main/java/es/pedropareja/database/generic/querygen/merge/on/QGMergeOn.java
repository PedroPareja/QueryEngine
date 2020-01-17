package es.pedropareja.database.generic.querygen.merge.on;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import es.pedropareja.database.generic.querygen.merge.when.QGLinkMergeWhen;

public interface QGMergeOn extends QGQuery, QGLinkConditions<QGMergeOn>, QGLinkMergeWhen
{

}
