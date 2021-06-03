package es.pedropareja.database.generic.querygen.from;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGFromPrv extends QGQueryMiddleEnd implements QGFrom, QGLinkOptionalPrv<QGFrom>
{
    private final QGExpression tableExp;

    QGFromPrv(QGExpression tableExp, QGQueryInit init)
    {
        super(init);
        this.tableExp = tableExp;
    }

    @Override
    public <U> void genOutput(StringBuilder stringBuilder, U context)
    {
        stringBuilder.append(" FROM ");

        tableExp.genExpressionOutput(stringBuilder, true, context);

        genOutputNext(stringBuilder, context);
    }

    @Override
    public QGFrom getThis()
    {
        return this;
    }
}
