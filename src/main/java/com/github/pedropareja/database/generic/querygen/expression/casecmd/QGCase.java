package com.github.pedropareja.database.generic.querygen.expression.casecmd;

import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;
import com.github.pedropareja.database.generic.querygen.expression.casecmd.when.QGWhen;
import com.github.pedropareja.database.generic.querygen.expression.operator.QGLinkOperators;

public interface QGCase
    extends QGExpression, QGLinkOperators, QGLinkAs
{
    QGWhen when();
    QGCase elseThen(QGExpression exp);
}
