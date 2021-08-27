package com.github.pedropareja.database.generic.querygen.select;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.DBTableAliasIndex;
import com.github.pedropareja.database.generic.querygen.auto.QGAutoFields;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.base.QGQueryInit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QGSelectPrv extends QGQueryInit implements QGSelect, QGAutoFields
{
    private List<QGExpression> expressionList;
    private boolean distinct = false;

    @SafeVarargs
    public QGSelectPrv(QGExpression ... expressions)
    {
        this.expressionList = new ArrayList<>(Arrays.asList(expressions));
    }

    public QGSelectPrv() { this.expressionList = null; }

    @Override
    public QGSelect distinct()
    {
        distinct = true;
        return this;
    }

    @Override
    public QGSelect and(QGExpression... expressions)
    {
        if(expressionList != null)
            expressionList.addAll(Arrays.asList(expressions));
        else
            expressionList = new ArrayList<>(Arrays.asList(expressions));

        return this;
    }

    @Override
    public QGSelect using(DBTableAliasIndex tableAliasIndex)
    {
        setTableAliasIndex(tableAliasIndex);
        return this;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = new ArrayList<>();

        if(expressionList != null)
            for(QGExpression expression: expressionList)
            {
                List<DBFieldInfo> autoFields = expression.getAutoFields();

                if(autoFields != null)
                    result.addAll(autoFields);
            }

        return result;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append("SELECT").append(distinct ? " DISTINCT " : " ");

        if(expressionList == null || expressionList.isEmpty())
            stringBuilder.append("*");
        else
        {
            for (int i = 0; i < expressionList.size(); i++)
            {
                stringBuilder.append(i != 0 ? ", " : "");
                //printField(stringBuilder, fieldList[i], fullNamespaces, context);
                expressionList.get(i).genExpressionOutput(stringBuilder, fullNamespaces, context);
            }
        }

        genOutputNext(stringBuilder, context);
    }
}
