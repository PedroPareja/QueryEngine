package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGOptionalityEnabled;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

public class QGConditionAllPrv<T extends QGOptionalityEnabled>
    implements QGConditionAll<T>, QGLinkConditionsPrv<QGConditionAll<T>, QGConditionAllPrv<T>>
{
    
}
