package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public class QGSetAssignmentPrv implements QGSetAssignment
{
    private final DBFieldInfo field;
    private final QGExpression value;

    public QGSetAssignmentPrv(DBFieldInfo field, QGExpression value)
    {
        this.field = field;
        this.value = value;
    }

    public QGSetAssignmentPrv(DBFieldInfo field)
    {
        this(field, null);
    }

    @Override
    public DBFieldInfo getField()
    {
        return field;
    }

    @Override
    public QGExpression getValue()
    {
        return value;
    }
}
