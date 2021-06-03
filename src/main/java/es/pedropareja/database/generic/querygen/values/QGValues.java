package es.pedropareja.database.generic.querygen.values;

import es.pedropareja.database.generic.querygen.base.QGQuery;
import es.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGValues extends QGQuery
{
    QGValues and(QGExpression... values);
}
