package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

import java.util.List;

public class QGAggregatePrv<T extends Enum<?> & DBFieldInfo>
        extends QGExpressionBase implements QGAggregate, QGLinkAsPrv
{
    private final Type type;
    private final T field;
    private final String id;

    private QGAggregatePrv(QGExpressionPrv init, Type type, T field, String id)
    {
        super(init);
        this.type = type;
        this.field = field;
        this.id = id;
    }

    private QGAggregatePrv(Type type, T field, String id)
    {
        super();
        this.type = type;
        this.field = field;
        this.id = id;
    }

    public QGAggregatePrv(QGExpressionPrv init, Type type, T field)
    {
        this(init, type, field, null);
    }

    public QGAggregatePrv(Type type, T field)
    {
        this(type, field, null);
    }

    public QGAggregatePrv(QGExpressionPrv init, Type type, String id)
    {
        this(init, type, null, id);
    }

    public QGAggregatePrv(Type type, String id)
    {
        this(type, null, id);
    }

    public QGAggregatePrv(Type type)
    {
        this(type, null, null);
    }

    @Override
    public  <U> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, U context)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append(type.name()).append("(");

        if(field != null)
            field.genExpressionOutput(stringBuilder, fullNamespaces, context);
        else if(id != null)
            stringBuilder.append(id);
        else
            stringBuilder.append("*");

        stringBuilder.append(")");
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return field.getAutoFields();
    }

}
