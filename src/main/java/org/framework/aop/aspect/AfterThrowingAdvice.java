package org.framework.aop.aspect;

import org.framework.aop.intercept.MethodInterceptor;
import org.framework.aop.intercept.MethodInvocation;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

/**
 * <p>异常通知</p>
 * Created by PYL
 */
public class AfterThrowingAdvice extends AbstractAspectJAdvice implements Advice, MethodInterceptor {

    private String throwingName;
    private MethodInvocation mi;

    public AfterThrowingAdvice(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public void setThrowingName(String name) {
        this.throwingName = name;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Exception {
        try {
            return mi.proceed();
        }
        catch (Throwable ex){
            super.invokeAdviceMethod(mi,null,ex.getCause());
            throw ex;
        }
    }
}
