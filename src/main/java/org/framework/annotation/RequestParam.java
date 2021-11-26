package org.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by PYL
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    String value() default "";
}
