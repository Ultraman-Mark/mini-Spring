package org.framework.aop;

/**
 * <p>AOP配置的封装对象</p>
 * Created by PYL
 */
public class AopConfig {
    /**
     * <p>切点</p>
     */
    private String pointCut;

    /**
     * <p>前置通知</p>
     */
    private String before;

    /**
     * <p>后置通知</p>
     */
    private String afterReturn;

    /**
     * <p>异常通知</p>
     */
    private String afterThrow;

    /**
     * <p>异常类型</p>
     */
    private String afterThrowClass;

    /**
     * <p>切面类</p>
     */
    private String aspectClass;

    public String getPointCut() {
        return pointCut;
    }

    public void setPointCut(String pointCut) {
        this.pointCut = pointCut;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfterReturn() {
        return afterReturn;
    }

    public void setAfterReturn(String afterReturn) {
        this.afterReturn = afterReturn;
    }

    public String getAfterThrow() {
        return afterThrow;
    }

    public void setAfterThrow(String afterThrow) {
        this.afterThrow = afterThrow;
    }

    public String getAfterThrowClass() {
        return afterThrowClass;
    }

    public void setAfterThrowClass(String afterThrowClass) {
        this.afterThrowClass = afterThrowClass;
    }

    public String getAspectClass() {
        return aspectClass;
    }

    public void setAspectClass(String aspectClass) {
        this.aspectClass = aspectClass;
    }
}
