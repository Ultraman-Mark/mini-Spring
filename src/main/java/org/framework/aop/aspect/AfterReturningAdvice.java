package org.framework.aop.aspect;

import org.framework.aop.intercept.MethodInterceptor;
import org.framework.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * <p>后置通知</p>
 * Created by PYL
 */
public class AfterReturningAdvice extends AbstractAspectJAdvice implements Advice, MethodInterceptor {

    public AfterReturningAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Exception {
        Object returnValue = mi.proceed();
        invokeAdviceMethod(mi,returnValue,null);
        return returnValue;
    }
}
