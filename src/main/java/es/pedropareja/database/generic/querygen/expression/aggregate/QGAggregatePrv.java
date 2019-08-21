package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;

public class QGAggregatePrv<T extends Enum<?> & DBFieldInfo>
        extends QGExpressionBase implements QGAggregate
{
    private final Type type;
    private final T field;
    private final String id;

    private QGAggregatePrv(QGExpressionBase init, Type type, T field, String id)
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

    public QGAggregatePrv(QGExpressionBase init, Type type, T field)
    {
        this(init, type, field, null);
    }

    public QGAggregatePrv(Type type, T field)
    {
        this(type, field, null);
    }

    public QGAggregatePrv(QGExpressionBase init, Type type, String id)
    {
        this(init, type, null, id);
    }

    public QGAggregatePrv(Type type, String id)
    {
        this(type, null, id);
    }

    @Override
    protected void genOutput(StringBuilder stringBuilder)
    {
        printSpaceIfNotFirst(stringBuilder);
        stringBuilder.append(type.name()).append("(");

        if(field != null)
            QGQueryBase.printField(stringBuilder, field, isFullNamespaces());
        else if(id != null)
            stringBuilder.append(id);

        stringBuilder.append(")");
    }
}
