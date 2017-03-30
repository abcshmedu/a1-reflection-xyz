package edu.hm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to read fields of implementing objects.
 *
 * @author J. Behrmann, J. Wittek
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface RenderMe {

    /**
     * "with"-key to set specific renderer-classes for fields.
     * @return full path to class
     */
    String with() default "";
}
