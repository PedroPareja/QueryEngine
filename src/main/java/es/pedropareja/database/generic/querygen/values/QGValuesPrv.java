package es.pedropareja.database.generic.querygen.values;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QGValuesPrv extends QGQueryMiddleEnd implements QGValues
{
    private final List<QGExpression> values = new ArrayList<>();

    @SafeVarargs
    public QGValuesPrv(QGQueryInit init, QGExpression ... values)
    {
        super(init);
        this.values.addAll(Arrays.asList(values));
    }

    @Override
    public QGValues and(QGExpression... values)
    {
        this.values.addAll(Arrays.asList(values));
        return this;
    }
    
    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        stringBuilder.append(" VALUES (");

        for(int i=0; i < values.size(); i++)
        {
            if(i!=0)
                stringBuilder.append(", ");

            values.get(i).genExpressionOutput(stringBuilder, true, context);
        }

        stringBuilder.append(")");
    }
}
