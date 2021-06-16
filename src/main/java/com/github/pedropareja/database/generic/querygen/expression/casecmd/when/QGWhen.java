package com.github.pedropareja.database.generic.querygen.expression.casecmd.when;

import com.github.pedropareja.database.generic.querygen.condition.QGLinkConditions;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.QGCase;

public interface QGWhen extends QGExpression, QGLinkConditions<QGWhen>
{
    QGCase then(QGExpression exp);
}
