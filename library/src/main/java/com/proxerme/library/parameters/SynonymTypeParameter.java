package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the types a {@link com.proxerme.library.connection.info.entity.Synonym} can
 * have.
 *
 * @author Ruben Gees
 */
public class SynonymTypeParameter {

    public static final String ORIGINAL = "name";
    public static final String ENGLISH = "nameeng";
    public static final String GERMAN = "nameger";
    public static final String JAPANESE = "namejap";

    private SynonymTypeParameter() {
    }

    /**
     * An annotation representing the available synonym types.
     */
    @StringDef({ORIGINAL, ENGLISH, GERMAN, JAPANESE})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface SynonymType {
    }
}
