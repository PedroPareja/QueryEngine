package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

public class QGConditionComparation <T extends Enum<?> & DBFieldInfo> implements QGConditionBase
{
    private final ComparationType type;
    private final T field1;
    private final T field2;

    public QGConditionComparation(ComparationType type, T field1, T field2)
    {
        this.type = type;
        this.field1 = field1;
        this.field2 = field2;
    }

    public QGConditionComparation(ComparationType type, T field)
    {
        this(type, field, null);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");

        QGQueryBase.printField(stringBuilder, field1, fullNamespaces, context);

        stringBuilder.append(" ").append(type.symbol).append(" ");

        if(field2 != null)
            QGQueryBase.printField(stringBuilder, field2, fullNamespaces, context);
        else
            stringBuilder.append("?");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionComparation))
            return false;

        QGConditionComparation<?> o = (QGConditionComparation) obj;

        return  type == o.type
                && field1.equalsField(o.field1)
                && (field2 != null ? field2.equalsField(o.field2) : o.field2 == null);
    }

    enum ComparationType
    {
        GREATER(">"),
        GREATER_EQUAL(">="),
        EQUALS("="),
        LESS_EQUAL("<="),
        LESS("<"),
        LIKE("LIKE");

        final String symbol;

        ComparationType(String symbol)
        {
            this.symbol = symbol;
        }
    }
}
