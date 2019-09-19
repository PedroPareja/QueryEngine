package es.pedropareja.database.generic.querygen.auto;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.DBTableMapper;
import es.pedropareja.database.generic.querygen.base.QGQueryBase;
import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.optional.QGLinkOptionalPrv;

public class QGAutoPrv<T extends Enum<?> & DBFieldInfo> extends QGQueryMiddleEnd implements QGAuto, QGLinkOptionalPrv<QGAuto, QGAutoPrv<T>>
{
    private final DBTableMapper tableMapper;
    private final Class<T> mainTable;

    public QGAutoPrv(DBTableMapper tableMapper, Class<T> mainTable, QGQueryInit init)
    {
        super(init);
        this.tableMapper = tableMapper;
        this.mainTable = mainTable;
    }

    public QGAutoPrv(DBTableMapper tableMapper, QGQueryInit init)
    {
        this(tableMapper, null, init);
    }

    @Override
    public <T> void genOutput(StringBuilder stringBuilder, T context)
    {
        
    }

    @Override
    public boolean equalsUntilHere(QGQueryBase q)
    {
        if(!(q instanceof QGAutoPrv))
            return false;

        QGAutoPrv instance = (QGAutoPrv) q;

        return tableMapper.equals(instance.tableMapper);
    }

    @Override
    public QGAuto getThis()
    {
        return this;
    }

    @Override
    public QGAutoPrv getPrv()
    {
        return this;
    }
}
