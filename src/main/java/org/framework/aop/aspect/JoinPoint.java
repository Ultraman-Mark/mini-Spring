package org.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 *  <p>连接点接口，定义切点的抽象</p>
 * Created by PYL
 */
public interface JoinPoint {
    /**
     * <p>业务方法本身</p>
     * */
    Method getMethod();

    /**
     * <p>该方法的参数列表</p>
     */
    Object[] getArguments();

    /**
     * <p>该方法对应的对象</p>
     */
    Object getThis();

    /**
     * <p>在joinPoint中添加自定义属性</p>
     */
    void setUserAttribute(String key, Object value);

    /**
     * <p>获取自定义属性</p>
     */
    Object getUserAttribute(String key);
}
