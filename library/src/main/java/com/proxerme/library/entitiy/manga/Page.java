package com.proxerme.library.entitiy.manga;

import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity representing a single page from a {@link Chapter}.
 *
 * @author Ruben Gees
 */
@Value
public class Page {

    /**
     * Returns the name of the page. To be used for retrieving the url.
     */
    @Getter(onMethod = @__({@NotNull}))
    private String name;

    /**
     * Returns the height of the page.
     */
    private int height;

    /**
     * Returns the width of the uploader if present.
     */
    @Getter(onMethod = @__({@NotNull}))
    private int width;
}
