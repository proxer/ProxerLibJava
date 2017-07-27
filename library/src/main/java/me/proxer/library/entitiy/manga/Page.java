package me.proxer.library.entitiy.manga;

import lombok.Getter;
import lombok.Value;

import javax.annotation.Nonnull;

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
    @Getter(onMethod = @__({@Nonnull}))
    private String name;

    /**
     * Returns the height of the page.
     */
    private int height;

    /**
     * Returns the width of the uploader if present.
     */
    @Getter(onMethod = @__({@Nonnull}))
    private int width;
}
