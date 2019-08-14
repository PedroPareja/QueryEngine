package es.pedropareja.database.generic;

public interface DBFieldInfo
{
    String getName();

    default <T extends Enum<?> & DBFieldInfo> boolean equalsField(T other)
    {
        if(other == null)
            return false;

        if (!this.getClass().equals(other.getClass()))
            return false;

        return this == other;
    }
}
