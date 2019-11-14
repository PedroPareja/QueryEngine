package es.pedropareja.database.generic.querygen.select;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.auto.QGAutoFields;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

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
        this.expressionList = Arrays.asList(expressions);
    }

    public QGSelectPrv() { this.expressionList = null; }

    @Override
    public QGSelect distinct()
    {
        distinct = true;
        return this;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = new ArrayList<>();

        for(QGExpression expression: expressionList)
            result.addAll(expression.getAutoFields());

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

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGSelectPrv))
            return false;

        QGSelectPrv qSelect = (QGSelectPrv) q;

        return fieldArrayEquals(expressionList, qSelect.expressionList) && distinct == qSelect.distinct;
    }
}
