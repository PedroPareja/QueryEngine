package es.pedropareja.database.generic.querygen.expression.casecmd;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.casecmd.when.QGWhen;
import es.pedropareja.database.generic.querygen.expression.casecmd.when.QGWhenPrv;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.ArrayList;
import java.util.List;

public class QGCasePrv extends QGExpressionBase
    implements QGCase, QGLinkOperatorsPrv, QGLinkAsPrv
{
    private List<QGWhen> whenList = new ArrayList<>();
    private QGExpression elseExp;

    @Override
    protected List<DBFieldInfo> getElementAutoFields()
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
