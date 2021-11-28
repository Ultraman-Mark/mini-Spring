package org.framework.aop.intercept;

/**
 * <p>方法拦截器接口</p>
 * Created by PYL
 */
public interface MethodInterceptor {
    Object invoke(MethodInvocation mi) throws Exception;
}
