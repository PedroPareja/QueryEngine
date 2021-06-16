package com.github.pedropareja.database.generic.querygen.expression.casecmd;

import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.when.QGWhenPrv;
import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;
import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.when.QGWhen;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.ArrayList;
import java.util.List;

public class QGCasePrv extends QGExpressionBase
    implements QGCase, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private List<QGWhen> whenList = new ArrayList<>();
    private QGExpression elseExp;

    public QGCasePrv() {}

    public QGCasePrv(QGExpressionPrv init)
    {
        super(init);
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        List<DBFieldInfo> result = null;

        for(QGWhen when: whenList)
            result = QGQueryBase.joinLists(result, when.getAutoFields());

        if(elseExp != null)
            result = QGQueryBase.joinLists(result, elseExp.getAutoFields());

        return result;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        printSpaceIfNotFirst(stringBuilder);

        stringBuilder.append("CASE");

        for(QGWhen when: whenList)
            when.genExpressionOutput(stringBuilder, fullNamespaces, context);

        if(elseExp != null)
        {
            stringBuilder.append(" ELSE ");
            elseExp.genExpressionOutput(stringBuilder, fullNamespaces, context);
        }

        stringBuilder.append(" END");
    }

    @Override
    public QGWhen when()
    {
        QGWhen result = new QGWhenPrv(this);
        whenList.add(result);
        return result;
    }

    @Override
    public QGCase elseThen(QGExpression exp)
    {
        this.elseExp = exp;
        return this;
    }
}
