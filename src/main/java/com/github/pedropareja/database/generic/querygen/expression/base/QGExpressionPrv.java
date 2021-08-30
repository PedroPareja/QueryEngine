package com.github.pedropareja.database.generic.querygen.expression.base;

import com.github.pedropareja.database.generic.DBFieldInfo;
import com.github.pedropareja.database.generic.querygen.base.QGQuery;
import com.github.pedropareja.database.generic.querygen.base.QGQueryBase;

import java.util.List;

public interface QGExpressionPrv extends QGExpression
{
    QGExpressionPrv getInit();
    QGExpressionPrv getNext();

    <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context);

    default <T> void genSequenceOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        genOutput(stringBuilder, fullNamespaces, query, context);

        if(getNext() != null)
            getNext().genSequenceOutput(stringBuilder, fullNamespaces, query, context);
    }

    @Override
    default <T> void genExpressionOutput(StringBuilder stringBuilder, boolean fullNamespaces, QGQuery query, T context)
    {
        getInit().genSequenceOutput(stringBuilder, fullNamespaces, query, context);
    }

    <T extends QGExpressionPrv> T assignNext(T next);

    @Override
    default List<DBFieldInfo> getAutoFields()
    {
        return getInit().getAutoFieldsSequence();
    }

    default List<DBFieldInfo> getAutoFieldsSequence()
    {
        if(getNext() != null)
            return QGQueryBase.joinLists(getElementAutoFields(), getNext().getAutoFieldsSequence());

        return getElementAutoFields();
    }

    List<DBFieldInfo> getElementAutoFields();
}
