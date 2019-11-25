package es.pedropareja.database.generic.querygen.expression.number;

import java.math.BigDecimal;

public interface QGLinkNumber
{
    QGNumber number(BigDecimal value);
    QGNumber number(int value);
    QGNumber number(String value);
}
