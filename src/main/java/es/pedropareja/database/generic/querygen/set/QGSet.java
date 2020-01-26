package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.where.QGLinkWhere;

public interface QGSet
        extends QGQuery, QGLinkWhere
{
    QGSet set(DBFieldInfo field);
    QGSet set(DBFieldInfo field, QGExpression value);
    QGSet set(QGSetAssignment ... setAssignments);
}
