package es.pedropareja.database.generic.querygen.expression.field;

import es.pedropareja.database.generic.DBFieldInfo;
import es.pedropareja.database.generic.querygen.expression.base.QGExpressionPrv;

public interface QGLinkFieldPrv extends QGLinkField, QGExpressionPrv
{
    @Override
    default <T extends Enum<?> & DBFieldInfo> QGField field(T fieldInfo)
    {
        return assignNext(new QGFieldPrv<>(getInit(), fieldInfo));
    }
}
