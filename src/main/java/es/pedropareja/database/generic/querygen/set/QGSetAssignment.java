package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGSetAssignment
{
    DBFieldInfo getField();
    QGExpression getValue();
}
