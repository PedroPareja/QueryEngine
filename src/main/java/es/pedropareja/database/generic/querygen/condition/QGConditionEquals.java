package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

public class QGConditionEquals<T extends Enum<?> & DBFieldInfo> implements QGConditionBase
{
    private final T field1;
    private final T field2;

    public QGConditionEquals(T field1, T field2)
    {
        this.field1 = field1;
        this.field2 = field2;
    }

    public QGConditionEquals(T field) { this(field, null); }

    @Override
    public void genOutput(StringBuilder stringBuilder, boolean fullNamespaces)
    {
        stringBuilder.append(" ");

        QGQueryBase.printField(stringBuilder, field1, fullNamespaces);

        stringBuilder.append(" = ");

        if(field2 != null)
            QGQueryBase.printField(stringBuilder, field2, fullNamespaces);
        else
            stringBuilder.append("?");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionEquals))
            return false;

        QGConditionEquals<?> o = (QGConditionEquals) obj;

        return field1.equalsField(o.field1)
                && (field2 != null ? field2.equalsField(o.field2) : o.field2 == null);
    }
}
