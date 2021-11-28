package org.framework.aop;

import org.framework.aop.intercept.MethodInterceptor;
import org.framework.aop.intercept.MethodInvocation;
import org.framework.aop.support.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>CGLIG代理</p>
 * Created by PYL
 */
public class CglibAopProxy implements AopProxy, MethodInterceptor {
    private Class targetClass;

    private Object target;

    private AdvisedSupport config;

    public CglibAopProxy(AdvisedSupport config) {
        this.config = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.config.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        this.targetClass = this.config.getTargetClass();
        this.target = this.config.getTarget();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.targetClass);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Exception {
        List<Object> interceptorsAndDynamicMethodMatchers =
                this.config.getInterceptorAndDynamicInterceptionAdvice(method, this.targetClass);
        MethodInvocation invocation =
                new MethodInvocation(proxy, method, this.target,
                        this.targetClass, args, interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Exception {
        return null;
    }
}
