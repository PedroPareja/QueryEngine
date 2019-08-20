package es.pedropareja.database.generic.querygen.expression.field;

import es.pedropareja.database.generic.DBFieldInfo;

public interface QGLinkField
{
    <T extends Enum<?> & DBFieldInfo> QGField field(T fieldInfo);
}
