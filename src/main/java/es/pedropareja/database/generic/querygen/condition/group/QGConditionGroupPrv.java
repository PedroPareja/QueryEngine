package es.pedropareja.database.generic.querygen.condition.group;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.base.QGQueryMiddleEnd;
import es.pedropareja.database.generic.querygen.condition.QGLinkConditionsPrv;

public class QGConditionGroupPrv<T extends QGQuery, U extends QGConditionGroup<T>>
    extends QGQueryMiddleEnd
    implements QGConditionGroup<T>, QGLinkConditionsPrv<>
{

}
