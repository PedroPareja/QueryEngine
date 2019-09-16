package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;

public class QGConditionLike<T extends Enum<?> & DBFieldInfo> implements QGConditionBase
{
    private final T field;

    public QGConditionLike(T field)
    {
        this.field = field;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        stringBuilder.append(" ");

        QGQueryBase.printField(stringBuilder, field, fullNamespaces, context);

        stringBuilder.append(" LIKE ?");
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof QGConditionLike))
            return false;

        QGConditionLike<?> o = (QGConditionLike<?>) obj;

        return field.equalsField(o.field);
    }
}
