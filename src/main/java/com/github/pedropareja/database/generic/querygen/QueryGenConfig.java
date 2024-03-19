package com.github.pedropareja.database.generic.querygen;

public class QueryGenConfig
{
    public static final boolean QUOTED_FIELDS = getEnvVar("QG_QUOTED_FIELDS", false, v -> Boolean.parseBoolean(v));

    private static <T> T getEnvVar(String name, T defaultValue, TypeConverter<T> typeConverter)
    {
        String valueStr = System.getenv(name);

        if(valueStr == null)
            return defaultValue;
        try
        {
            return typeConverter.getValue(valueStr);
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    @FunctionalInterface
    private interface TypeConverter<T>
    {
        T getValue(String valueStr) throws Exception;
    }
}
