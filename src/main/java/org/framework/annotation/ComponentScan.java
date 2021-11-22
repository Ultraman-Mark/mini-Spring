package org.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by PYL
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {
    String value() default "";
}
