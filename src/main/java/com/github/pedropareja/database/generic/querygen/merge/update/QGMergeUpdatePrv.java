package com.github.pedropareja.database.generic.querygen.merge.update;

import com.github.pedropareja.database.generic.querygen.set.QGSetAssignment;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;
import com.github.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;
import com.github.pedropareja.database.generic.querygen.set.QGSetAssignmentPrv;

import java.util.ArrayList;
import java.util.List;

public class QGMergeUpdatePrv extends QGQueryMiddleEnd implements QGMergeUpdate, QGLinkOptionalPrv<QGMergeUpdate>
{
    private final List<QGSetAssignment> setAssignments = new ArrayList<>();

    public QGMergeUpdatePrv(QGQueryInit init)
    {
        super(init);
    }

    @Override
    public QGMergeUpdate set(DBFieldInfo field, QGExpression value)
    {
        if(getNextOptionalAppearanceValueAndReset())
            setAssignments.add(new QGSetAssignmentPrv(field, value));

        return this;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" UPDATE SET ");

        for(int i=0; i < setAssignments.size(); i++)
        {
            if(i != 0)
                stringBuilder.append(", ");

            setAssignments.get(i).getField().genExpressionOutput(stringBuilder, false, context);
            stringBuilder.append(" = ");

            if(setAssignments.get(i).getValue() != null)
                setAssignments.get(i).getValue().genExpressionOutput(stringBuilder, true, context);
            else
                stringBuilder.append("?");
        }

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGMergeUpdate getThis()
    {
        return this;
    }
}
