package es.pedropareja.database.generic.querygen.expression.param;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.as.QGLinkAsPrv;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionBase;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

import java.util.List;

public class QGParamPrv extends QGExpressionBase implements QGParam, QGLinkAsPrv
{
    private final int repetitions;

    public QGParamPrv()
    {
        this.repetitions = 1;
    }

    public QGParamPrv(int repetitions)
    {
        this.repetitions = repetitions;
    }

    public QGParamPrv(QGExpressionPrv init)
    {
        super(init);
        this.repetitions = 1;
    }

    public QGParamPrv(QGExpressionPrv init, int repetitions)
    {
        super(init);
        this.repetitions = repetitions;
    }

    @Override
    public List<DBFieldInfo> getElementAutoFields()
    {
        return null;
    }

    @Override
    public int getRepetitions()
    {
        return repetitions;
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context)
    {
        printSpaceIfNotFirst(stringBuilder);

        for(int i=0; i < repetitions; i++)
        {
            if(i!=0)
                stringBuilder.append(", ");

            stringBuilder.append("?");
        }
    }
}
