package es.pedropareja.database.generic.querygen.merge.update;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.merge.when.QGLinkMergeWhen;

public interface QGMergeUpdate extends QGQuery, QGLinkMergeWhen
{
    QGMergeUpdate set(DBFieldInfo field, QGExpression value);
}
