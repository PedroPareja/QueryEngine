package es.pedropareja.database.generic.querygen.merge.using.as;

import es.pedropareja.database.generic.querygen.base.QGQueryInit;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;

public class QGMergeUsingAsPrv extends QGQueryMiddleEnd implements QGMergeUsingAs
{
    private final String id;

    public QGMergeUsingAsPrv(QGQueryInit init, String id)
    {
        super(init);
        this.id = id;
    }

    
}
