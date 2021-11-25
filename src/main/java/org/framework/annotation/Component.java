package org.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by PYL
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component{
    String value() default "";
}
