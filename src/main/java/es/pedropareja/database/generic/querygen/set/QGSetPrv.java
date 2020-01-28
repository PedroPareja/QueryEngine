package es.pedropareja.database.generic.querygen.set;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.ArrayList;
import java.util.List;

public class QGSetPrv extends QGQueryMiddleEnd implements QGSet
{
    private final List<QGSetAssignment> setAssignments = new ArrayList<>();

    @SafeVarargs
    public QGSetPrv(QGQueryInit init, DBFieldInfo ... fieldList)
    {
        super(init);

        for(DBFieldInfo field: fieldList)
            setAssignments.add(new QGSetAssignmentPrv(field));
    }

    public QGSetPrv(QGQueryInit init, DBFieldInfo field, QGExpression value)
    {
        super(init);

        setAssignments.add(new QGSetAssignmentPrv(field, value));
    }

    public QGSetPrv(QGQueryInit init, QGSetAssignment ... setAssignments)
    {
        super(init);

        for(QGSetAssignment setAssignment: setAssignments)
            this.setAssignments.add(setAssignment);
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" SET ");

        for(int i=0; i < setAssignments.size(); i++)
        {
            if(i != 0)
                stringBuilder.append(", ");

            setAssignments.get(i).getField().genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), context);
            stringBuilder.append(" = ");

            if(setAssignments.get(i).getValue() != null)
                setAssignments.get(i).getValue().genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), context);
            else
                stringBuilder.append("?");
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGSet set(DBFieldInfo ... fields)
    {
        for(DBFieldInfo field: fields)
            setAssignments.add(new QGSetAssignmentPrv(field));

        return this;
    }

    @Override
    public QGSet set(DBFieldInfo field, QGExpression value)
    {
        setAssignments.add(new QGSetAssignmentPrv(field, value));
        return this;
    }

    @Override
    public QGSet set(QGSetAssignment... setAssignments)
    {
        for(QGSetAssignment setAssignment: setAssignments)
            this.setAssignments.add(setAssignment);

        return this;
    }
}
