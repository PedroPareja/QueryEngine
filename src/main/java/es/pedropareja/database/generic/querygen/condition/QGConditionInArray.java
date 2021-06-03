package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class QGConditionInArray implements QGConditionBase
{
    private final QGExpression field;
    private final QGExpression[] values;

    public QGConditionInArray(QGExpression field, QGExpression[] values)
    {
        this.field = field;
        this.values = values;
    }

    @Override
    public <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        if(!isNull())
        {
            stringBuilder.append(" ");
            field.genExpressionOutput(stringBuilder, fullNamespaces, context);
            stringBuilder.append(" IN(");

            for (int i = 0; i < values.length; i++)
            {
                stringBuilder.append(i != 0 ? ", " : "");
                values[i].genExpressionOutput(stringBuilder, fullNamespaces, context);
            }

            stringBuilder.append(")");
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        QGConditionInArray that = (QGConditionInArray) o;

        return Objects.equals(field, that.field) &&
                Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(field);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public List<DBFieldInfo> getAutoFields()
    {
        List<DBFieldInfo> result = new ArrayList<>(field.getAutoFields());

        for(QGExpression expression: values)
            result = QGQueryBase.joinLists(result, expression.getAutoFields());

        return result;
    }

    @Override
    public boolean isNull()
    {
        return values.length == 0;
    }
}
