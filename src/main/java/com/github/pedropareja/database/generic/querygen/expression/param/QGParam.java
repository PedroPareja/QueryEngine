package com.github.pedropareja.database.generic.querygen.expression.param;

import com.github.pedropareja.database.generic.querygen.expression.as.QGLinkAs;
import com.github.pedropareja.database.generic.querygen.expression.base.QGExpression;

public interface QGParam extends QGExpression, QGLinkAs
{
    int getRepetitions();
}
