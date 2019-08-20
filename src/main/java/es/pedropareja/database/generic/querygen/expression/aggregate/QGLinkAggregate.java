package es.pedropareja.database.generic.querygen.expression.aggregate;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.field.QGField;

public interface QGLinkAggregate extends QGField
{
    <T extends Enum<?> & DBFieldInfo> QGAggregate avg(T field);
    QGAggregate avg(String id);

    <T extends Enum<?> & DBFieldInfo> QGAggregate count(T field);
    QGAggregate count(String id);
    QGAggregate count();

    <T extends Enum<?> & DBFieldInfo> QGAggregate min(T field);
    QGAggregate min(String id);

    <T extends Enum<?> & DBFieldInfo> QGAggregate max(T field);
    QGAggregate max(String id);

    <T extends Enum<?> & DBFieldInfo> QGAggregate sum(T field);
    QGAggregate sum(String id);
}
