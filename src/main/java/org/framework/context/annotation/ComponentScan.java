package org.framework.context.annotation;

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
