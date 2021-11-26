package org.framework.annotation;

import java.lang.annotation.*;

/**
 * <p>url映射注解</p>
 * Created by PYL
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
