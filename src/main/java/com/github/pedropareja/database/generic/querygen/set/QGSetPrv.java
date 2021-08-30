package com.github.pedropareja.database.generic.querygen.set;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.exceptions.QueryGenException;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

import java.util.ArrayList;
import java.util.List;

public class QGSetPrv extends QGQueryMiddleEnd implements QGSet, QGLinkOptionalPrv<QGSet>
{
    private final List<QGSetAssignment> setAssignments = new ArrayList<>();

    public QGSetPrv(QGQueryInit init)
    {
        super(init);
    }

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
        if(setAssignments.isEmpty())
            throw new QueryGenException("There must be at least one SET statement in an UPDATE");

        stringBuilder.append(" SET ");

        for(int i=0; i < setAssignments.size(); i++)
        {
            if(i != 0)
                stringBuilder.append(", ");

            setAssignments.get(i).getField().genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), this, context);
            stringBuilder.append(" = ");

            if(setAssignments.get(i).getValue() != null)
                setAssignments.get(i).getValue().genExpressionOutput(stringBuilder, getInit().isFullNamespaces(), this, context);
            else
                stringBuilder.append("?");
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGSet set(DBFieldInfo ... fields)
    {
        if(getNextOptionalAppearanceValueAndReset())
            for(DBFieldInfo field: fields)
                setAssignments.add(new QGSetAssignmentPrv(field));

        return this;
    }

    @Override
    public QGSet set(DBFieldInfo field, QGExpression value)
    {
        if(getNextOptionalAppearanceValueAndReset())
            setAssignments.add(new QGSetAssignmentPrv(field, value));

        return this;
    }

    @Override
    public QGSet set(QGSetAssignment... setAssignments)
    {
        if(getNextOptionalAppearanceValueAndReset())
            for(QGSetAssignment setAssignment: setAssignments)
                this.setAssignments.add(setAssignment);

        return this;
    }

    @Override
    public QGSet getThis()
    {
        return this;
    }
}
