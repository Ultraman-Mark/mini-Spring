package org.framework.aop;

/**
 * <p>代理工厂接口</p>
 * Created by PYL
 */
public interface AopProxy {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
