package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.*;
import lombok.experimental.FieldDefaults;
import me.proxer.library.enums.MediaLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class Episode {

    /**
     * Returns the id.
     */
    @Json(name = "no")
    private int number;

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "typ")
    private MediaLanguage language;
}
