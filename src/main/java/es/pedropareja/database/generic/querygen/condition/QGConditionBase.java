package es.pedropareja.database.generic.querygen.condition;

import es.pedropareja.database.generic.querygen.auto.QGAutoFields;

public interface QGConditionBase extends QGAutoFields
{
    <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context);

    boolean isNull();
}
