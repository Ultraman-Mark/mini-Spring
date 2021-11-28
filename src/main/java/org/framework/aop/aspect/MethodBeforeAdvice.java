package org.framework.aop.aspect;

import org.framework.aop.intercept.MethodInterceptor;
import org.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * <p>前置通知</p>
 * Created by PYL
 */
public class MethodBeforeAdvice extends AbstractMethodError implements Advice, MethodInterceptor {

    public MethodBeforeAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Exception {
        super.invokeAdviceMethod(mi, null, null);
        return mi.proceed();
    }
}
