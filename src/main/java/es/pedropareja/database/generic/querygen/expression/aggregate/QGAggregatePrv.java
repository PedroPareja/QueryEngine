package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;
import es.pedropareja.database.generic.querygen.expression.operator.QGLinkOperatorsPrv;

import java.util.List;

public class QGAggregatePrv
        extends QGExpressionBase implements QGAggregate, QGLinkAsPrv, QGLinkOperatorsPrv
{
    private final Type type;
    private final QGExpression exp;

    public QGAggregatePrv(QGExpressionPrv init, Type type, QGExpression exp)
    {
        super(init);
        this.type = type;
        this.exp = exp;
    }

    public QGAggregatePrv(Type type, QGExpression exp)
    {
        super();
        this.type = type;
        this.exp = exp;
    }

    @Override
    public  <U> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, U context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append(type.name()).append("(");

        exp.genExpressionOutput(stringBuilder, fullNamespaces, context);

        stringBuilder.append(")");
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return exp.getAutoFields();
    }
}
