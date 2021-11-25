package org.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by PYL
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
    String value() default "";
}
