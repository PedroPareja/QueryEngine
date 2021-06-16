package com.github.pedropareja.database.generic.querygen.base;

public interface QGOptionalityEnabled
{
    boolean getNextOptionalAppearanceValueAndReset();
    void setNextOptionalAppearanceValue(boolean nextOptionalAppearanceValue);
}
