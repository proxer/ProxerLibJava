package com.proxerme.library.parameters;

import android.support.annotation.StringDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing all the available types of an industry.
 *
 * @author Ruben Gees
 */
public class IndustryTypeParameter {

    public static final String PUBLISHER = "publisher";
    public static final String STUDIO = "studio";
    public static final String PRODUCER = "producer";
    public static final String RECORD_LABEL = "record_label";
    public static final String TALENT_AGENT = "talent_agent";
    public static final String STREAMING = "streaming";

    private IndustryTypeParameter() {
    }

    /**
     * An annotation representing the available industry types.
     */
    @StringDef({PUBLISHER, STUDIO, PRODUCER, RECORD_LABEL, TALENT_AGENT, STREAMING})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface IndustryType {
    }

}
