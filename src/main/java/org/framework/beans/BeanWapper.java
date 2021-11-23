package org.framework.beans;

/**
 * <p>bean的包装类</p>
 * Created by PYL
 */
public class BeanWapper {

    /**
     * <p>返回由该类封装的bean实例</p>
     * */
    private Object wrapperInstance;

    /**
     *  <p>返回包装的bean类型</>
     * */
    private Class<?> wrappedClass;

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Class<?> getWrappedClass() {
        return wrappedClass;
    }

    public void setWrappedClass(Class<?> wrappedClass) {
        this.wrappedClass = wrappedClass;
    }
}
