package es.pedropareja.database.generic.querygen.condition;

public interface QGConditionBase
{
    <T> void genOutput(StringBuilder stringBuilder, boolean fullNamespaces, T context);
}
