package me.proxer.library.util;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.*;

/**
 * This annotation can be applied to a package, class or method to indicate that all return values in that element are
 * nonnull by default unless overridden.
 */
@Target(ElementType.PACKAGE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Nonnull
@TypeQualifierDefault(ElementType.METHOD)
public @interface ReturnTypesAreNonnullByDefault {
}
