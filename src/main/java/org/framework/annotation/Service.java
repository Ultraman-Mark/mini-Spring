package org.framework.annotation;

import java.lang.annotation.*;

/**
 * <p>服务模式注解</p>
 * @author Bosen
 * @date 2021/9/10 14:18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
    String value() default "";
}